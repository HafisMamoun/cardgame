 package com.openclassrooms.cardgame.view;

import java.util.Scanner; 
import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.model.Suit;
import com.openclassrooms.cardgame.model.Hand;
import com.openclassrooms.cardgame.model.IPlayer;
import com.openclassrooms.cardgame.model.PlayingCard; 
import com.openclassrooms.cardgame.model.Rank;

import com.openclassrooms.cardgame.controller.GameController;

public class CommandLineView implements GameViewable {
	GameController controller;
	Scanner keyboard = new Scanner(System.in);
	private String suit;
	private String rank; 

	public void setController(GameController controller) {
		this.controller = controller; 
	}
	public void promptForPlayerName() {
		System.out.println("Enter player name");  
		String name = keyboard.nextLine();
		if (name.isEmpty()) { 
			controller.startGame();
		} else { 
			controller.addPlayer(name); 
		}
	}
	public void promptForFlip() {
		System.out.println("Press enter to reveal cards"); 
		keyboard.nextLine();
		controller.flipCards(); 
	}
	public void promptForNewGame() {
		System.out.println("Press enter to deal again"); 
		keyboard.nextLine();
		controller.startGame(); 
	} 
	public void showPlayerName(int playerIndex, String playerName){
		System.out.println("[" + playerIndex + "][" + playerName + "]");
	}
	public void showFaceDownCardForPlayer(int playerIndex, String playerName){
		System.out.println("[" + playerName + "][][][]");
	}
	public void showCardForPlayer(int playerIndex, String playerName){
		System.out.println("[" + rank + "] [" + suit + "]");
	}
	public void showWinner (String winnerName) {
		System.out.println(" Winner! " +winnerName); 	
	}
	@Override
	public void showCardForPlayer(int playerIndex, String playerName, String string, String string2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void add(GameViewable view) {
		// TODO Auto-generated method stub
		
	}
}
