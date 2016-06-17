package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.Level.ScrGame;
import com.mygdx.game.Screens.ScrLostRespawn;
import com.mygdx.game.Screens.ScrMainMenu;
import com.mygdx.game.Screens.ScrOptions;

public class GameMain extends Game {
	ScrOptions scrOptions;
	String sPlayer, sLvl;

	boolean[] arbUnlockedLevel= new boolean[2];

	//got this screen switching code from the intothewoods group:https://github.com/spidermanchild/IntoTheWoodsMultScreens
	public void setScreen(ScreenId screenId) {
		switch (screenId) {
			case MENU:
				super.setScreen(new ScrMainMenu(this));
				break;
			case GAME:
				super.setScreen(new ScrGame(this,sPlayer,sLvl));
				break;
            case DEAD:
                super.setScreen(new ScrLostRespawn(this));
                break;
            case OPTIONS:
                super.setScreen(scrOptions);
                break;
		}
        //scrOptions.update(arbUnlockedLevel);
	}

	@Override
	public void create () {
        scrOptions = new ScrOptions(this,arbUnlockedLevel);
        setScreen(ScreenId.MENU);
	}

    public void initializeGame(){
        sPlayer=scrOptions.sPlayer;//pick player class to use
        System.out.println("Menu: "+sPlayer);
        sLvl = scrOptions.sLvl;//pick level map to use
        System.out.println("Menu: "+ sLvl);
        setScreen(ScreenId.GAME);
    }

	public enum ScreenId {
		//main game states
		DEAD, GAME, MENU, OPTIONS
	}
}
