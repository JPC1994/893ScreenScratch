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
public class ScrMainMenu implements Screen, InputProcessor{
    public SpriteBatch batch = new SpriteBatch();
    Stage stage;
    GameMain game;
    private Texture textureMenu;
    private Sprite spriteMenu;
    TextButton btnStart;
    TextButton btnOptions;
    TextButton btnLvlSelect;
    TextButton btnCharSelect;
    BtnBaseStyle textButtonStyle = new BtnBaseStyle();

    //TODO: Get a proper end screen and button image for the respawn screen
    public ScrMainMenu(GameMain game) {
        this.game = game;
        textureMenu=new Texture(Gdx.files.internal("images/wall.png"));
        spriteMenu=new Sprite(textureMenu);
        spriteMenu.setPosition(0,0);
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
                game.currentState = GameMain.GameState.GAME;
                game.updateScreen();
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
                game.currentState = GameMain.GameState.OPTIONS;
                System.out.println("hit");
                game.updateScreen();
                return true;
            }
        });
        stage.addActor(btnOptions);

        btnLvlSelect= new TextButton("Select Level",textButtonStyle);
        btnLvlSelect.setSize(210f, 50f);
        btnLvlSelect.setPosition(210f,100f);
        btnLvlSelect.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.currentState = GameMain.GameState.LVLSELECT;
                game.updateScreen();
                System.out.println("hit");
                return true;
            }
        });
        stage.addActor(btnLvlSelect);

        btnCharSelect= new TextButton("Select Character",textButtonStyle);
        btnCharSelect.setSize(210f, 50f);
        btnCharSelect.setPosition(210f,140f);
        btnCharSelect.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.currentState = GameMain.GameState.CHARSELECT;
                game.updateScreen();
                System.out.println("hit");
                return true;
            }
        });
        stage.addActor(btnCharSelect);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act();
        spriteMenu.draw(batch);
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
