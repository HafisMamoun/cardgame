package com.openclassrooms.cardgame;

import com.openclassrooms.cardgame.DeckFactory.DeckType;
import com.openclassrooms.cardgame.controller.GameController;
import com.openclassrooms.cardgame.games.GameEvaluator;
import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.view.CommandLineView;
import com.openclassrooms.cardgame.view.GameSwing;
import com.openclassrooms.cardgame.view.View;

public class CardGame {

	public static void main(String[] args) { 
		GameSwing gs = new GameSwing(); 
		gs.createAndShowGUI(); 
		GameController gc = new GameController(gs, 
						DeckFactory.makeDeck(DeckType.Normal),
						new GameEvaluator());
		gc.run();
	}
}
