package com.mygdx.game.Level;

import com.badlogic.gdx.Game;
import com.mygdx.game.Screens.ScrCharSelect;
import com.mygdx.game.Screens.ScrLostRespawn;
import com.mygdx.game.Screens.ScrLvlSelect;
import com.mygdx.game.Screens.ScrMainMenu;
import com.mygdx.game.Screens.ScrOptions;

public class GameMain extends Game {
	public GameState currentState;
	ScrLostRespawn scrRespawn;
	ScrGame scrGame;
	ScrMainMenu scrMenu;
	ScrCharSelect scrCharSelect;
	ScrLvlSelect scrLvlSelect;
	ScrOptions scrOptions;

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
		/*else if(currentState== GameState.OPTIONS) {
			setScreen(scrOptions);
		}*/
		else if(currentState== GameState.CHARSELECT) {
			setScreen(scrMenu);
		}
		else if(currentState== GameState.LVLSELECT) {
			setScreen(scrLvlSelect);
		}
	}

	@Override
	public void create () {

		scrGame = new ScrGame(this);
		scrRespawn= new ScrLostRespawn(this);
		scrMenu = new ScrMainMenu(this);
		scrCharSelect = new ScrCharSelect(this);
		scrLvlSelect = new ScrLvlSelect(this);
		scrOptions = new ScrOptions(this);
		currentState = GameState.LVLSELECT; //Set the current state to the main menu, and update it.
		updateScreen();
	}

	public enum GameState {
		//main game states
		DEAD, GAME, MENU, CHARSELECT, LVLSELECT, OPTIONS
	}
}
