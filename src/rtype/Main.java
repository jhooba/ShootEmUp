package rtype;

import generator.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Timer;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector2f;
import rtype.entity.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
  private static final int SCREEN_WIDTH = 800;
  private static final int SCREEN_HEIGHT = 600;
  private static final boolean FULL_SCREEN = false;
  private static final int BYTES_PER_PIXEL = 3;

  // Default values for sprites
  private static float DEFAULT_Z = 0;
  private static final Vector2f DEFAULT_SCROLLING_SPEED = new Vector2f(-5, 0);

  // This is the address where screen copy is stored
  private int screenTextureId = 0;
  // Generators generate entity for the game
  private GeneratorSet generator;
  private TextureLoader textureLoader;

  // This is the player's sprite
  private PlayerShip player;
  // Set of layers, drawn in a different order ( see render method)
  private Layer bullets = new Layer();
  private Layer enemies = new Layer();
  private Layer fx = new Layer();
  private Layer bonus = new Layer();
  private Layer background = new Layer();
  private Layer frontground = new Layer();
  private Layer text = new Layer();
  // Text object used to display fps, score and entity total count
  private Text textFPS;
  private Text textHiScore;
  private TextEntityCounter entitiesCount;
  private Text pause;

  private GeneratorBase homingGenerator = new HomingMissileGenerator();
  private GeneratorBase ladyBirdGenerator = new LadyBirdGenerator(30);

  private Timer timer = new Timer();
  private float lastTime = timer.getTime();
  // Measure time elapsed since last frame renderer
  // This is the heart variable of the engine
  private float tick;
  private float deltas = 0;

  private boolean gameOff = false;
  private float fadeAlpha = 0;

  public static void main(String[] args) {
    Main inst = new Main();
    inst.run();
  }

  private Main() {
    init();
    initGL();
  }

  private void init() {
    Mouse.setGrabbed(false);
    try {
      createWindow(SCREEN_WIDTH, SCREEN_HEIGHT, FULL_SCREEN);
      createOffScreenBuffer();
      generator = new GeneratorSet();
      generator.addGenerator(new StarGenerator());
      generator.addGenerator(new IntroGenerator());
      generator.addGenerator(new EnemyWave(100, EnemyWave.RATE));
      generator.addGenerator(new EnemyWave(150, EnemyWave.RATE / 2));

      generator.addGenerator(new EnemyWave(-100, EnemyWave.RATE + 10));
      generator.addGenerator(new EnemyWave(-150, EnemyWave.RATE / 2 + 10));

      generator.addGenerator(new EnemyWave(0, EnemyWave.RATE + 20));
      generator.addGenerator(new EnemyWave(50, EnemyWave.RATE / 2 + 20));

      generator.addGenerator(new LadyBirdGenerator(EnemyWave.RATE / 2 + 30));
    } catch (LWJGLException e) {
      e.printStackTrace();
    }
  }

  private void initGL() {
    GL11.glEnable(GL11.GL_TEXTURE_2D);
    GL11.glClearColor(0.f, 0.f, 0.f, 0.f);
    GL11.glClearDepth(1.f);
    GL11.glDisable(GL11.GL_DEPTH_TEST);
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glDepthMask(false);
    GL11.glMatrixMode(GL11.GL_PROJECTION);  // Select The Projection Matrix
    GL11.glLoadIdentity();  // Reset The Projection Matrix

    GLU.gluOrtho2D(-SCREEN_WIDTH / 2, SCREEN_WIDTH / 2, -SCREEN_HEIGHT / 2, SCREEN_HEIGHT / 2);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);

    textureLoader = new TextureLoader();
    textureLoader.init();
  }

  private void run() {
    Intro intro = new Intro(this);
    intro.play();

    BonusDesc bonusDesc = new BonusDesc(this);
    bonusDesc.play();
    
    addBasicEntities();

    player.addEventListeners();

    Bonus b = BounsFactory.createBonus();
    b.spawn(new Vector2f(player.position.x + 100, player.position.y), DEFAULT_SCROLLING_SPEED, bonus);

    addControlKeys();

    while (!gameOff) {
      heartBeat();
      getEntries();
      update();
      checkCollisions();
      render();
    }
    Display.destroy();
  }

  private void render() {
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);  // Clear the Screen and the Depth Buffer
    background.render();

    // Make background darker
    fadeAlpha = 0.65f;
    fadeScreen(false);
    fadeAlpha = 0;
  }

  private void fadeScreen(boolean drawFb) {
    if (fadeAlpha > 0.1) {
      GL11.glLoadIdentity();
      GL11.glTranslatef(0, 0, DEFAULT_Z);
      GL11.glColor4f(0, 0, 0, fadeAlpha / 1.2f);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      GL11.glBegin(GL11.GL_QUADS);
      {
        GL11.glVertex2f(SCREEN_WIDTH / 2, -SCREEN_HEIGHT / 2);
        GL11.glVertex2f(-SCREEN_WIDTH / 2, -SCREEN_HEIGHT / 2);
        GL11.glVertex2f(-SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        GL11.glVertex2f(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
      }
      GL11.glEnd();
      GL11.glColor4f(1, 1, 1, 1);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    }
    if (player != null && player.orb != null && player.orb.fb != null
        && player.orb.fb.displayAnimation && drawFb) {
      player.orb.fb.updateTick();
      player.orb.fb.draw(fadeAlpha * 2);
    }
  }

  private void checkCollisions() {
    // Check bullets with enemies
    ArrayList<Entity> bulletsArray = bullets.entities;
    ArrayList<Entity> enemiesArray = enemies.entities;
    for (int i = 0; i < bulletsArray.size(); ++i) {
      for (int j = 0; j < enemiesArray.size(); ++j) {
        if (j < 0) {
          continue;
        }
        if (i < 0) {
          break;
        }
        Entity currentBullet = bulletsArray.get(i);
        Entity currentEnemy = enemiesArray.get(j);
        if (Collision.boxBoxOverlap(currentBullet, currentEnemy)) {
          ++player.hiScore;
          if (currentBullet.collided(currentEnemy)) {
            --i;
          }
          if (currentEnemy.collided(currentBullet)) {
            --j;
          }
        }
      }
    }
    textHiScore.setString("HISCORE:" + player.hiScore);

    // Check players with bonuses
    ArrayList<Entity> bonusArray = bonus.entities;
    for (int i = 0; i < bonusArray.size(); ++i) {
      Entity currentBonus = bonusArray.get(i);
      if (Collision.boxBoxOverlap(player, currentBonus)) {
        if (currentBonus.collided(player)) {
          --i;
          player.collided(currentBonus);
        }
      }
    }
  }

  private void update() {
    bullets.update();
    enemies.update();
    fx.update();
    background.update();
    bonus.update();
    frontground.update();
    text.update();
  }

  private void getEntries() {
    if (exitRequested()) {
      gameOff = true;
    }
    EventManager.instance().checkEvents();
  }

  private boolean exitRequested() {
    return Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Display.isCloseRequested();
  }

  private void heartBeat() {
    Timer.tick();
    tick = timer.getTime() - lastTime;
    deltas += tick;
    lastTime = timer.getTime();
  }

  private void addControlKeys() {
    KeyListener pauseKeyEvent = new KeyListener(){
      @Override
      public void onKeyDown() {
        if (timer.isPaused()) {
          timer.resume();
          pause.unSpawn();
        } else {
          pause = new Text("GAME PAUSED");
          pause.spawn(new Vector2f(-50, 0), new Vector2f(0, 0), frontground);
          timer.pause();
        }
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_P, pauseKeyEvent);

    KeyListener homingMissileKeyEvent = new KeyListener(){
      @Override
      public void onKeyDown() {
        if (generator.contains(homingGenerator)) {
          generator.removeGenerator(homingGenerator);
        } else {
          generator.addGenerator(homingGenerator);
        }
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_F1, homingMissileKeyEvent);

    KeyListener enemiesKeyEvent = new KeyListener(){
      @Override
      public void onKeyDown() {
        if (generator.contains(ladyBirdGenerator)) {
          generator.removeGenerator(ladyBirdGenerator);
        } else {
          generator.addGenerator(ladyBirdGenerator);
        }
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_F2, enemiesKeyEvent);
  }

  private void addBasicEntities() {
    player = new PlayerShip();
    player.spawn(new Vector2f(-150, -100), new Vector2f(0, 0), bullets);
    player.addBooster();

    textFPS = new Text("");
    textFPS.spawn(new Vector2f(SCREEN_WIDTH / 2 - 100, SCREEN_WIDTH / 2 - 20), new Vector2f(0, 0), text);

    textHiScore = new Text("HISCORE:");
    textHiScore.spawn(new Vector2f(SCREEN_WIDTH / 2 + 20, SCREEN_WIDTH / 2 - 20), new Vector2f(0, 0), text);

    entitiesCount = new TextEntityCounter();
    entitiesCount.spawn(new Vector2f(SCREEN_WIDTH / 2 + 20, SCREEN_WIDTH / 2 + 20), new Vector2f(0, 0), text);
  }

  private void createWindow(int screenWidth, int screenHeight, boolean fullScreen) throws LWJGLException {
    if (!fullScreen) {
      Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
    } else {
      Display.setFullscreen(true);
      try {
        DisplayMode dm[] = org.lwjgl.util.Display.getAvailableDisplayModes(320, 240, -1, -1, -1, -1, 60, 85);
        org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
            "width=" + screenWidth, "height=" + screenHeight, "freq=85",
            "bpp=" + Display.getDisplayMode().getBitsPerPixel()
        });
      } catch(Exception e) {
        Sys.alert("Error", "Could not start full screen, switching to windowed mode");
        Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
      }
    }
    Display.create();
  }

  private void createOffScreenBuffer() {
    ByteBuffer scratch = ByteBuffer.allocateDirect(1024 * 1024 * BYTES_PER_PIXEL);
    IntBuffer buf = ByteBuffer.allocateDirect(12).order(ByteOrder.nativeOrder()).asIntBuffer();
    GL11.glGenTextures(buf);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, buf.get(0));
    screenTextureId = buf.get(0);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, 1024, 1024, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, scratch);
  }
}
