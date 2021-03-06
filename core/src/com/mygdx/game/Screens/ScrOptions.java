package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.GameMain;

/**
 * Created by Rueban Rasaselvan on 10/05/2016.
 */
public class ScrOptions implements Screen, InputProcessor{
    GameMain game;

    private Sprite spriteBack, spriteLocked;
    public SpriteBatch batch = new SpriteBatch();
    public SpriteBatch backbatch = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    Stage stage;
    /*TextureAtlas taVolume;
    TextButton.TextButtonStyle volumeButtonStyle = new TextButton.TextButtonStyle();*/
    Animation aniIdle, aniRun;

    private Label lblCharSelect, lblLvlSelect, lblVolToggle, lblChar1, lblChar2, lblLvl1, lblLvl2;
    private Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

    ImageButton imgbtnChar1, imgbtnChar2, imgBtnLvl1, imgBtnLvl2;
    ImgBtnBaseStyle imgBtnBaseStyle;
    TextButton btnReturn;
    TxtBtnBaseStyle textButtonStyle = new TxtBtnBaseStyle();

    public String sLvl="Level1";
    public String sPlayer="player1";

    boolean isIdle = true, bRight;
    boolean[] arbLvlUnlocked = new boolean[2];

    float elapsedTime;
    int width;
    int height;

    public ScrOptions(GameMain game, boolean[] arbLvlUnlocked) {
        this.game = game;
        spriteBack=new Sprite(new Texture(Gdx.files.internal("images/wall.png")));
        this.arbLvlUnlocked=arbLvlUnlocked;
        spriteLocked=new Sprite(new Texture(Gdx.files.internal("images/locked edit.png")));
        spriteLocked.setSize(200f,150f);
        spriteLocked.setPosition(350f,100f);
    }

    public void update(boolean[] arbWin){
        this.arbLvlUnlocked=arbWin;
    }

    @Override
    public void show() {
        TextureAtlas taRun = new TextureAtlas(Gdx.files.internal(sPlayer+"/run/run.pack"));
        TextureAtlas taIdle = new TextureAtlas(Gdx.files.internal(sPlayer+"/idle/idle.pack"));

        // An easier way to populate an animation:
        aniIdle = new Animation(10, taIdle.getRegions());
        aniRun = new Animation(10, taRun.getRegions());
        TextureRegion textureRegion = aniIdle.getKeyFrame(0f, true);
        width = textureRegion.getRegionWidth();
        height = textureRegion.getRegionHeight();

        stage= new Stage();
        Gdx.input.setInputProcessor(stage);

        //got Mr grondin's button images from the button scratch: https://github.com/Mrgfhci
        btnReturn= new TextButton("Return",textButtonStyle);
        btnReturn.setSize(210f, 50f);
        btnReturn.setPosition(210f,0);
        btnReturn.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(GameMain.ScreenId.MENU);
                return true;
            }
        });
        stage.addActor(btnReturn);

        //currently using the same player image
        //to differentiate between the different players, using the different animation frames for the images
        imgBtnBaseStyle = new ImgBtnBaseStyle("player1/idle/idle.pack","idle (1)","idle (2)");
        imgbtnChar1=new ImageButton(imgBtnBaseStyle);
        imgbtnChar1.setSize(75f,100f);
        imgbtnChar1.setPosition(75f,300f);
        imgbtnChar1.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                sPlayer="player1";
                bRight=true;
                isIdle=false;
                System.out.println(sPlayer);
                return true;
            }
        });
        stage.addActor(imgbtnChar1);

        //currently using the same player image
        //to differentiate between the different players, using the different animation frames for the images
        imgBtnBaseStyle = new ImgBtnBaseStyle("player2/idle/idle.pack","idle (1)","idle (2)");
        imgbtnChar2=new ImageButton(imgBtnBaseStyle);
        imgbtnChar2.setSize(75f,100f);
        imgbtnChar2.setPosition(200f,300f);
        imgbtnChar2.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                sPlayer="player2";
                bRight=true;
                isIdle=false;
                System.out.println(sPlayer);
                return true;
            }
        });
        stage.addActor(imgbtnChar2);

        imgBtnBaseStyle=new ImgBtnBaseStyle("player1/idle/idle.pack","idle (1)","idle (2)");
        imgBtnLvl1 = new ImageButton(imgBtnBaseStyle);
        //imgBtnLvl1.setSize(210f, 50f);
        imgBtnLvl1.setPosition(150,100f);
        imgBtnLvl1.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                sLvl="Level1";
                arbLvlUnlocked[0]=true;
                System.out.println(sLvl);
                return true;
            }
        });
        stage.addActor(imgBtnLvl1);

        //TODO: level images to use for choice selection
        imgBtnBaseStyle=new ImgBtnBaseStyle("player2/idle/idle.pack","idle (1)","idle (2)");
        imgBtnLvl2= new ImageButton(imgBtnBaseStyle);
        //imgBtnLvl2.setSize(210f, 50f);
        imgBtnLvl2.setPosition(450f,200f);
        imgBtnLvl2.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (arbLvlUnlocked[0]) {
                    sLvl = "Level2";
                    System.out.println(sLvl);
                }
                else{
                    System.out.println("Level Locked");
                }
                return true;
            }
        });
        stage.addActor(imgBtnLvl2);

        lblCharSelect=new Label("Select a Character:",labelStyle);
        lblCharSelect.setPosition(25f,415f);
        stage.addActor(lblCharSelect);

        lblChar1=new Label("Player 1",labelStyle);
        lblChar1.setPosition(82f,280f);
        stage.addActor(lblChar1);

        lblChar2=new Label("Player 2",labelStyle);
        lblChar2.setPosition(210f,280f);
        stage.addActor(lblChar2);

        lblLvlSelect=new Label("Select a Level:",labelStyle);
        lblLvlSelect.setPosition(25f,250f);
        stage.addActor(lblLvlSelect);

        lblLvl1=new Label("Level 1",labelStyle);
        lblLvl1.setPosition(125f,50f);
        stage.addActor(lblLvl1);

        lblLvl2=new Label("Level 2",labelStyle);
        lblLvl2.setPosition(450f,50f);
        stage.addActor(lblLvl2);

        lblVolToggle=new Label("Toggle Volume:",labelStyle);
        lblVolToggle.setPosition(400f,450f);
        stage.addActor(lblVolToggle);
    }

    @Override
    public void render(float delta) {
        elapsedTime++;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw background
        backbatch.begin();
        spriteBack.draw(backbatch);
        backbatch.end();

        //allow button functionatlity
        batch.begin();
        stage.act();
        //animate buttons
        draw(batch);
        //lock levels
        if(!arbLvlUnlocked[0]){
            spriteLocked.draw(batch);
        }
        batch.end();

        //draw buttons onto the stage
        stage.draw();
    }

    void draw(SpriteBatch sb) {
        // drawing sprite on main body using default library, not using animatedbox2dsprite because it doesn't loop the animation
        float x = imgbtnChar2.getX() + 275f;
        float y = imgbtnChar2.getY() + 50f;

        TextureRegion textureRegion;

        if(isIdle)
            textureRegion = aniIdle.getKeyFrame(elapsedTime, true);

        else
            textureRegion = aniRun.getKeyFrame(elapsedTime, true);

        int width = (int) ((textureRegion.getRegionWidth())*4.5);
        int height = (int) ((textureRegion.getRegionHeight())*4.5);

        if(bRight)
            sb.draw(textureRegion, x - width / 4f, y - height / 4f, width / 2f, height / 2f);

        else
            sb.draw(textureRegion, x + width / 4f, y - height / 4f, -width / 2f, height / 2f);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
