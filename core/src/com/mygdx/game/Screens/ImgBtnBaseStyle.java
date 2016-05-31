package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Rueban Rasaselvan on 23/05/2016.
 */

public class ImgBtnBaseStyle extends ImageButton.ImageButtonStyle {
    Skin skin = new Skin();
    TextureAtlas taAtlas;

    public ImgBtnBaseStyle(String SpriteFile, String sBtnUp, String sBtnDown) {
        taAtlas = new TextureAtlas(Gdx.files.internal(SpriteFile));
        skin.addRegions(taAtlas);
        this.up = skin.getDrawable(sBtnUp);
        this.down = skin.getDrawable(sBtnDown);
    }
}

