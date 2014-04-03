package com.badlogic.gradletest;

import java.util.ArrayList;
import java.util.List;

// How to represent a card
// This is part of our model
public class Card {
	public static enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES };
	private Suit suit;
	public static enum Rank { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, JACK, QUEEN, KING };
	private Rank rank;
	
	public Card (Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	public static List<Card> deck() {
		List<Card> deck = new ArrayList<Card>();
		
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				deck.add(new Card(s, r));
			}
		}
		return deck;
	}
}
