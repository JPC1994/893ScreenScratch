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
import com.mygdx.game.GameMain;

/**
 * Created by Rueban Rasaselvan on 10/05/2016.
 */
public class ScrMainMenu implements Screen, InputProcessor{
    public SpriteBatch batch = new SpriteBatch();
    Stage stage;
    GameMain game;
    private Texture textureBack;
    private Sprite spriteBack;
    TextButton btnStart;
    TextButton btnOptions;
    TxtBtnBaseStyle textButtonStyle = new TxtBtnBaseStyle();

    //TODO: Get a proper end screen and button image for the respawn screen
    public ScrMainMenu(GameMain game) {
        this.game = game;
        textureBack=new Texture(Gdx.files.internal("images/wall.png"));
        spriteBack=new Sprite(textureBack);
    }


    @Override
    public void show() {
        stage= new Stage();
        Gdx.input.setInputProcessor(stage);
        //got Mr grondin's button images from the button scratch: https://github.com/Mrgfhci
        btnStart= new TextButton("Start",textButtonStyle);
        btnStart.setSize(210f, 50f);
        btnStart.setPosition(210f,20f);
        btnStart.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.initializeGame();
                return true;
            }
        });
        stage.addActor(btnStart);

        btnOptions= new TextButton("Options",textButtonStyle);
        btnOptions.setSize(210f, 50f);
        btnOptions.setPosition(210f,60f);
        btnOptions.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(GameMain.ScreenId.OPTIONS);
                return true;
            }
        });
        stage.addActor(btnOptions);
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
