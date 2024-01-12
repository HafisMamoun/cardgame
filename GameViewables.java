package com.openclassrooms.cardgame.view;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.cardgame.controller.GameController;

public class GameViewables implements GameViewable {
	List<GameViewable> views; 
	
	public GameViewables () { 
		views = new ArrayList<GameViewable> (); 
	}
	
	public void addViewable (GameViewable view) { 
		views.add(view);
	}

	@Override
	public void setController(GameController controller) {
		for (GameViewable view : views) {
			view.setController(controller);
		}
	}

	@Override
	public void promptForPlayerName() {
		for (GameViewable view : views) {
			view.promptForPlayerName(); 
		}
	}

	@Override
	public void showPlayerName(int playerIndex, String playerName) { 
		for (GameViewable view : views) {
			view.showPlayerName(playerIndex, name);
		}
	}

	@Override
	public void showFaceDownCardForPlayer(int playerIndex, String playerName) {
		for (GameViewable view : views) {
			view.showFaceDownCardForPlayer(playerIndex, name);
		}
	}

	@Override
	public void promptForFlip() {
		for (GameViewable view : views) {
			view.promptForFlip();
		}
		
	}

	@Override
	public void showCardForPlayer(int playerIndex, String playerName, String string, String string2) {
		for (GameViewable view : views) {
			view.showCardForPlayer(playerIndex, name, rank, suit);
		}	}

	@Override
	public void showWinner(String winnerName) {
		for (GameViewable view : views) {
			view.showWinner(winnerName);
		}
	}

	@Override
	public void promptForNewGame() {
		for (GameViewable view : views) {
			view.promptForNewGame();
		}
		
	}

	@Override
	public void add(GameViewable view) {
		// TODO Auto-generated method stub
		
	}

}
