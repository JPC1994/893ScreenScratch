package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    public SpriteBatch batch = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    Stage stage;
    /*TextureAtlas taVolume;
    TextButton.TextButtonStyle volumeButtonStyle = new TextButton.TextButtonStyle();*/
    ImageButton imgbtnChar1, imgbtnChar2, imgBtnLvl1, imgBtnLvl2;
    private Label lblCharSelect, lblLvlSelect, lblVolToggle;
    private Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
    ImgBtnBaseStyle imgBtnBaseStyle;
    TextButton btnReturn;
    TxtBtnBaseStyle textButtonStyle = new TxtBtnBaseStyle();
    GameMain game;
    public String sLvl="Level1";
    public String sPlayer="player1";
    private Texture textureBack;
    private Sprite spriteBack;

    //TODO: Get a proper end screen and button image for the respawn screen
    public ScrOptions(GameMain game) {
        this.game = game;
        textureBack=new Texture(Gdx.files.internal("images/wall.png"));
        spriteBack=new Sprite(textureBack);
    }


    @Override
    public void show() {
        stage= new Stage();
        Gdx.input.setInputProcessor(stage);
        //got Mr grondin's button images from the button scratch: https://github.com/Mrgfhci
        btnReturn= new TextButton("Return",textButtonStyle);
        btnReturn.setSize(210f, 50f);
        btnReturn.setPosition(210f,0);
        btnReturn.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO: Update game back to main menu instead of the game screen
                game.currentState = GameMain.GameState.MENU;
                game.updateScreen();
                return true;
            }
        });
        stage.addActor(btnReturn);

        //TODO: Replace image
       /* taVolume = new TextureAtlas(Gdx.files.internal("images/UpButton.pack"));
        //skin.addRegions(taVolume);
        //volumeButtonStyle.up = skin.getDrawable("MusicOn");
        btnReturn= new TextButton("",volumeButtonStyle);
        btnReturn.setSize(210f, 50f);
        btnReturn.setPosition(210f,100f);
        btnReturn.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO: Toggle music on and off
                /*game.currentState = GameMain.GameState.MENU;
                game.updateScreen();*/

        /*        return true;
            }
        });
        stage.addActor(btnReturn);*/

        //currently using the same player image
        //to differentiate between the different players, using the different animation frames for the images
        imgBtnBaseStyle = new ImgBtnBaseStyle("player1/idle/idle.pack","idle (1)","idle (2)");
        imgbtnChar1=new ImageButton(imgBtnBaseStyle);
        imgbtnChar1.setSize(75f,100f);
        imgbtnChar1.setPosition(100f,300f);
        imgbtnChar1.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                sPlayer="player1";
                System.out.println(sPlayer);
                return true;
            }
        });
        stage.addActor(imgbtnChar1);

        //currently using the same player image
        //to differentiate between the different players, using the different animation frames for the images
        imgBtnBaseStyle = new ImgBtnBaseStyle("player2/run/run.pack","run (1)","run (2)");
        imgbtnChar2=new ImageButton(imgBtnBaseStyle);
        imgbtnChar2.setSize(75f,100f);
        imgbtnChar2.setPosition(460f,300f);
        imgbtnChar2.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                sPlayer="player2";
                System.out.println(sPlayer);
                return true;
            }
        });
        stage.addActor(imgbtnChar2);

        imgBtnBaseStyle=new ImgBtnBaseStyle("player2/run/run.pack","run (1)","run (2)");
        imgBtnLvl1 = new ImageButton(imgBtnBaseStyle);
        //imgBtnLvl1.setSize(210f, 50f);
        imgBtnLvl1.setPosition(50f,200f);
        imgBtnLvl1.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                sLvl="Level1";
                System.out.println(sLvl);
                return true;
            }
        });
        stage.addActor(imgBtnLvl1);

        //TODO: level images to use for choice selection
        //TODO: Dynamic player choices
        imgBtnBaseStyle=new ImgBtnBaseStyle("player1/run/run.pack","run (1)","run (2)");
        imgBtnLvl2= new ImageButton(imgBtnBaseStyle);
        //imgBtnLvl2.setSize(210f, 50f);
        imgBtnLvl2.setPosition(370f,200f);
        imgBtnLvl2.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                sLvl="Level2";
                System.out.println(sLvl);
                return true;
            }
        });
        stage.addActor(imgBtnLvl2);

        lblCharSelect=new Label("Select a Character:",labelStyle);
        lblCharSelect.setPosition(25f,400f);
        stage.addActor(lblCharSelect);

        lblLvlSelect=new Label("Select a Level:",labelStyle);
        lblLvlSelect.setPosition(25f,250f);
        stage.addActor(lblLvlSelect);

        lblVolToggle=new Label("Toggle Volume:",labelStyle);
        lblVolToggle.setPosition(400f,450f);
        stage.addActor(lblVolToggle);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act();
        spriteBack.draw(batch);
        batch.end();
        stage.draw();
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
