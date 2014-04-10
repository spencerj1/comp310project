package com.badlogic.PokerGame;

import java.util.ArrayList;
import java.util.List;

public class Card {
	public static enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES};
	private Suit suit;
	public static enum Rank {
		ACE("ace"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("jack"), QUEEN("queen"), KING("king");
		String name;
		Rank(String name) {
			this.name = name;
		}
	};
	private Rank rank;
	private String image;
	
	public Card (Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
		this.image = toString();
	}
	public static List<Card> deck() {
		List<Card> deck = new ArrayList<Card>();
		
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				Card tempCard = new Card(s, r);
				deck.add(tempCard);
				System.out.println(tempCard.image);
			}
		}
		return deck;
	}
	public String toString() {
		return String.format("%s_of_%s.png", rank.name.toLowerCase(), suit.name().toLowerCase());
	}
	public String getImage() {
		return this.image;
	}
}
