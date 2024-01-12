package com.openclassrooms.cardgame.controller;

import java.util.ArrayList;

import com.openclassrooms.cardgame.games.GameEvaluator;
import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.model.IPlayer;
import com.openclassrooms.cardgame.model.Player;
import com.openclassrooms.cardgame.model.PlayingCard;
import com.openclassrooms.cardgame.model.WinningPlayer;
import com.openclassrooms.cardgame.view.GameViewable; 


public class GameController { 
	enum GameState {
		AddingPlayers, 
		CardsDealt, 
		WinnerRevealed
	}
	
	Deck deck;
	ArrayList<IPlayer> players; 
	IPlayer winner; 
	GameViewable view; 
	GameState gameState; 
	GameEvaluator evaluator;
	
	public GameController(GameViewable view, Deck deck, GameEvaluator evaluator) { 
		this.view = view;
		this.deck = deck; 
		this.evaluator = evaluator;
		this.evaluator = new GameEvaluator();
		players = new ArrayList<IPlayer>(); 
		gameState = GameState.AddingPlayers; 
		view.setController(this); 
	}
	
	public void run() { 
		while (true) { 
			switch (gameState) { 
			case AddingPlayers: 
			view.promptForPlayerName();
				break; 
			case CardsDealt: 
				view.promptForFlip(); 
				break; 
			case WinnerRevealed: 
				view.promptForNewGame(); 
				break;
			}
		} 
	}
	public void addPlayer(String playerName) { 
		if (gameState == GameState.AddingPlayers) {
				players.add(new Player (playerName)); 
				view.showPlayerName(players.size(), playerName); 
		}
	}
	public void startGame() {
		if (gameState != GameState.CardsDealt) { 
			deck.shuffle(); 
			int playerIndex = 1;
			for (IPlayer player : players) { 
				player.addCardToHand(deck.removeTopCard()); 
				view.showFaceDownCardForPlayer(playerIndex++, player.getName());
			}
			gameState = GameState.CardsDealt; 
		}
	} 
	
	public void flipCards() {
		int playerIndex = 1; 
		for (IPlayer player : players) { 
			PlayingCard pc = player.getCard(0); 
			pc.flip();
			view.showCardForPlayer(playerIndex++, player.getName(),
					pc.getRank().toString(), pc.getSuit().toString()); 
		}
		
		evaluateWinner();
		displayWinner(); 
		rebuildDeck(); 
		gameState = GameState.WinnerRevealed; 
	}
	
	public void restartGame() {
		rebuildDeck();
		gameState = GameState.AddingPlayers; 
	}
	
	void evaluateWinner() {
		winner = new WinningPlayer(evaluator.evaluateWinner(players));
	}
		
	void displayWinner() { 
		view.showWinner(winner.getName());
	}
		
	void rebuildDeck() { 
		for (IPlayer player : players) { 
			deck.returnCardToDeck(player.remvoveCard());
		}
	}
}