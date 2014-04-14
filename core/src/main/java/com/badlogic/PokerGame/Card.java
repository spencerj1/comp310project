package com.badlogic.PokerGame;

import java.util.ArrayList;
import java.util.List;

public class Card {
	public static enum Suit {SPADES, DIAMONDS, CLUBS, HEARTS};
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
		if(rank.name.toLowerCase().equals("jack") || rank.name.toLowerCase().equals("queen")
				 || rank.name.toLowerCase().equals("king")){
			return String.format("%s_of_%s2.png", rank.name.toLowerCase(), suit.name().toLowerCase());
		}else{
		return String.format("%s_of_%s.png", rank.name.toLowerCase(), suit.name().toLowerCase());
		}
	}
	public String getImage() {
		return image;
	}
	public int intRank() {
			 if (rank == Card.Rank.TWO) return 2;
		else if (rank == Card.Rank.THREE) return 3;
		else if (rank == Card.Rank.FOUR) return 4;
		else if (rank == Card.Rank.FIVE) return 5;
		else if (rank == Card.Rank.SIX) return 6;
		else if (rank == Card.Rank.SEVEN) return 7;
		else if (rank == Card.Rank.EIGHT) return 8;
		else if (rank == Card.Rank.NINE) return 9;
		else if (rank == Card.Rank.TEN) return 10;
		else if (rank == Card.Rank.JACK) return 11;
		else if (rank == Card.Rank.QUEEN) return 12;
		else if (rank == Card.Rank.KING) return 13;
		else if (rank == Card.Rank.ACE) return 14;
		return 0;
	}
	public int intSuit() {
			 if (suit == Card.Suit.SPADES) return 1;
		else if (suit == Card.Suit.DIAMONDS) return 2;
		else if (suit == Card.Suit.CLUBS) return 3;
		else if (suit == Card.Suit.HEARTS) return 4;
		return 0;
	}
}
