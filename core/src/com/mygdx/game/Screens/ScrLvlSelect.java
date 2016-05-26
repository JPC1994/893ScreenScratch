package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Level.BtnBaseStyle;
import com.mygdx.game.Level.GameMain;

/**
 * Created by Rueban Rasaselvan on 10/05/2016.
 */
public class ScrLvlSelect implements Screen, InputProcessor{
    public SpriteBatch batch = new SpriteBatch();
    Stage stage;
    GameMain game;
    private Texture textureLvl1;
    private Sprite spriteLvl1;
    private Texture textureLvl2;
    private Sprite spriteLvl2;
    TextButton btnReturn;
    BtnBaseStyle textButtonStyle = new BtnBaseStyle();

    //TODO: Get a proper end screen and button image for the respawn screen
    public ScrLvlSelect(GameMain game) {
        this.game = game;
        textureLvl1=new Texture(Gdx.files.internal("images/Smiley.png"));
        spriteLvl1=new Sprite(textureLvl1);
        textureLvl2=new Texture(Gdx.files.internal("images/Smiley.png"));
        spriteLvl2=new Sprite(textureLvl2);
    }


    @Override
    public void show() {
        stage= new Stage();
        Gdx.input.setInputProcessor(stage);

        //temporary image
        spriteLvl1.setPosition(80f, 280f);
        spriteLvl1.setSize(150f, 150f);

        //temporary image
        spriteLvl2.setPosition(400f, 280f);
        spriteLvl2.setSize(150f, 150f);

        //got Mr grondin's button images from the button scratch: https://github.com/Mrgfhci
        btnReturn= new TextButton("Return",textButtonStyle);
        btnReturn.setSize(210f, 50f);
        btnReturn.setPosition(210f,30f);
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

        btnReturn= new TextButton("Level 1",textButtonStyle);
        btnReturn.setSize(210f, 50f);
        btnReturn.setPosition(50f,200f);
        btnReturn.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO: Update game back to main menu instead of the game screen
                game.currentState = GameMain.GameState.GAME;
                game.updateScreen();
                return true;
            }
        });
        stage.addActor(btnReturn);

        btnReturn= new TextButton("Level 2",textButtonStyle);
        btnReturn.setSize(210f, 50f);
        btnReturn.setPosition(370f,200f);
        btnReturn.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO: Update game back to main menu instead of the game screen
                game.currentState = GameMain.GameState.GAME;
                game.updateScreen();
                return true;
            }
        });
        stage.addActor(btnReturn);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act();
        spriteLvl1.draw(batch);
        spriteLvl2.draw(batch);
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
