package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.Level.ScrGame;
import com.mygdx.game.Screens.ScrLostRespawn;
import com.mygdx.game.Screens.ScrMainMenu;
import com.mygdx.game.Screens.ScrOptions;

public class GameMain extends Game {
	public GameState currentState;
	ScrLostRespawn scrRespawn;
	ScrGame scrGame;
	ScrMainMenu scrMenu;
	ScrOptions scrOptions;
	String sPlayer, sLvl;

	//got this screen switching code from the intothewoods group:https://github.com/spidermanchild/IntoTheWoodsMultScreens
	public void updateScreen(){
		if(currentState== GameState.GAME){
			setScreen(scrGame);
		}else if(currentState== GameState.DEAD) {
			setScreen(scrRespawn);
		}
		else if(currentState== GameState.MENU) {
			setScreen(scrMenu);
		}
		else if(currentState== GameState.OPTIONS) {
			setScreen(scrOptions);
		}
	}

	//currently all screen are being preloaded before the options can be toggled in the options screen
    //therefore, options will not reload data used for processing the level
	@Override
	public void create () {
		scrRespawn= new ScrLostRespawn(this);
		scrMenu = new ScrMainMenu(this);
		scrOptions = new ScrOptions(this);
		//TODO: Lock levels until completed sequentially
		currentState = GameState.MENU; //Set the current state to the main menu, and update it.
		updateScreen();
	}

    public void initializeGame(){
        sPlayer=scrOptions.sPlayer;//pick player class to use
        System.out.println("Menu: "+sPlayer);
        sLvl = scrOptions.sLvl;//pick level map to use
        System.out.println("Menu: "+ sLvl);
        scrGame = new ScrGame(this,sPlayer,sLvl);
    }

	public enum GameState {
		//main game states
		DEAD, GAME, MENU, OPTIONS
	}
}
