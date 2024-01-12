package com.openclassrooms.cardgame.model;

public interface IPlayer { 
	public String getName(); 
	
	public void addCardToHand(PlayingCard pc);
	
	public PlayingCard getCard(int index); 
	
	public PlayingCard remvoveCard(); 

}
