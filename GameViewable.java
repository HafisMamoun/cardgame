 package com.openclassrooms.cardgame.view;

import com.openclassrooms.cardgame.controller.GameController;

public interface GameViewable { 
	void setController(GameController controller); 
	
	void promptForPlayerName();
	void showPlayerName(int playerIndex, String playerName); 
	
	void showFaceDownCardForPlayer(int playerIndex, String playerName); 
	
	void promptForFlip();
	void showCardForPlayer(int playerIndex, String playerName, String string, String string2); 
	void showWinner (String winnerName); 
	
	void promptForNewGame();

	void add(GameViewable view);
}