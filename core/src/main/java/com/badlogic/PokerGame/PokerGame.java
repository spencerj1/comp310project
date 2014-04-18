package com.badlogic.PokerGame;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

	
public class PokerGame extends ApplicationAdapter {
	Stage stage;
	boolean quit = false;
	boolean sortHover = false;
	boolean swapHover = false;
	boolean checkHover = false;
	boolean callHover = false;
	boolean foldHover = false;
	boolean Hover5 = false;
	boolean Hover25 = false;
	boolean Hover50 = false;
	boolean Hover100 = false;
	
	int cardHeight = 70;
	SpriteBatch batch;
	Texture backgroundTexture, menuTexture, balanceTexture;
	Sprite background, menu, balance;
	//long time = System.currentTimeMillis();
	ImageButton img1, img2, img3, img4, img5, imgDeck, bet5, bet50, bet25, bet100, cardback1,
	cardback2, cardback3, cardback4, cardback5, aicard1, aicard2, aicard3, aicard4, aicard5, woodbackground;
	ImageButton btnComp, btnUser, btnSort, btnSwap, btnCheck, btnFold, btnCall;

	Skin skin;
	List<Card> deck, hand = new ArrayList<Card>(), aihand;
	
	boolean[] swap = new boolean[5];  // keeps track of which cards you want swapped
	
	@Override
	public void create () {
		
		//skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();
		
		// The stage will respond to input from Gdx (keyboard, mouse, touch, game controller)
		Gdx.input.setInputProcessor(stage);
		
		// Create the user interface of the game
		buildUI();
		
		// Create a deck of cards to use
		deck = Card.deck();
		
		// Draw the cards (should later be put in a buttonListener method)
		deck = shuffle(deck);
		hand = drawCards(deck);
		/*hand.add(deck.get(9)); // test with certain cards
		hand.add(deck.get(10));
		hand.add(deck.get(11));
		hand.add(deck.get(12));
		hand.add(deck.get(0));*/
		aihand = drawCards(deck);
		updateCardImg();
		updateAiCards();
		addCardListeners();
	}
		
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, (float)0.5, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		checkHover();
		
		// Draw Sprites (background)
		batch.begin();
		background.draw(batch);
		menu.draw(batch);
		balance.draw(batch);
		batch.end();
		// Draw Imagebuttons and other actors (foreground)
		stage.act();
		stage.draw();
		
		if(quit)
			Gdx.app.exit();
	}
	
	@Override
    public void dispose() {
		// dispose of Spritebatch and all textures
        batch.dispose();
        backgroundTexture.dispose();
    }
	
	public void checkHover(){
		// Check mouse hover over btnSort
		if(sortHover)
			if(!btnSort.isOver()) {
				sortHover = false;
				btnSort.remove();
				btnSort = new ImageButton(new SpriteDrawable(new Sprite(new Texture("sortPlain.png"))));
				btnSort.setSize(160, 80);
				btnSort.setPosition(10, 0);
				stage.addActor(btnSort);
			}
			else;
		else
			if(btnSort.isOver()) {
				sortHover = true;
				btnSort.remove();
				btnSort = new ImageButton(new SpriteDrawable(new Sprite(new Texture("sortGlow.png"))));
				btnSort.setSize(160, 80);
				btnSort.setPosition(10, 0);
				stage.addActor(btnSort);
				addHoverBtnListeners();
			}
		
		// Check mouse hover over btnSwap
		if(swapHover)
			if(!btnSwap.isOver()) {
				swapHover = false;
				btnSwap.remove();
				btnSwap = new ImageButton(new SpriteDrawable(new Sprite(new Texture("swapPlain.png"))));
				btnSwap.setSize(260, 130);
				btnSwap.setPosition(250, -30);
				stage.addActor(btnSwap);
			}
			else;
		else
			if(btnSwap.isOver()) {
				swapHover = true;
				btnSwap.remove();
				btnSwap = new ImageButton(new SpriteDrawable(new Sprite(new Texture("swapGlow.png"))));
				btnSwap.setSize(260, 130);
				btnSwap.setPosition(250, -30);
				stage.addActor(btnSwap);
				addHoverBtnListeners();
			}
		
		//Check mouse over btnCheck
		if(checkHover)
			if(!btnCheck.isOver()) {
				checkHover = false;
				btnCheck.remove();
				btnCheck = new ImageButton(new SpriteDrawable(new Sprite(new Texture("checkPlain.png"))));
				btnCheck.setPosition(815, 600);
				stage.addActor(btnCheck);
			}
			else;
		else
			if(btnCheck.isOver()) {
				checkHover = true;
				btnCheck.remove();
				btnCheck = new ImageButton(new SpriteDrawable(new Sprite(new Texture("checkGlow.png"))));
				btnCheck.setPosition(815, 600);
				stage.addActor(btnCheck);
				addHoverBtnListeners();
			}
		
		//Check mouse over btnCall
		if(callHover)
			if(!btnCall.isOver()) {
				callHover = false;
				btnCall.remove();
				btnCall = new ImageButton(new SpriteDrawable(new Sprite(new Texture("callPlain.png"))));
				btnCall.setPosition(828, 700);
				stage.addActor(btnCall);
			}
			else;
		else
			if(btnCall.isOver()) {
				callHover = true;
				btnCall.remove();
				btnCall = new ImageButton(new SpriteDrawable(new Sprite(new Texture("callGlow.png"))));
				btnCall.setPosition(828, 700);
				stage.addActor(btnCall);
				addHoverBtnListeners();
			}
		
		//Check mouse over btnFold
		if(foldHover)
			if(!btnFold.isOver()) {
				foldHover = false;
				btnFold.remove();
				btnFold = new ImageButton(new SpriteDrawable(new Sprite(new Texture("foldPlain.png"))));
				btnFold.setPosition(830, 500);
				stage.addActor(btnFold);
			}
			else;
		else
			if(btnFold.isOver()) {
				foldHover = true;
				btnFold.remove();
				btnFold = new ImageButton(new SpriteDrawable(new Sprite(new Texture("foldGlow.png"))));
				btnFold.setPosition(830, 500);
				stage.addActor(btnFold);
				addHoverBtnListeners();
			}
		
		//Check mouse is over bet5
		if(Hover5)
			if(!bet5.isOver()) {
				Hover5 = false;
				bet5.setSize(110, 110);
				bet5.setPosition(845, 160);
			}
			else;
		else
			if(bet5.isOver()) {
				Hover5 = true;
				bet5.setSize(120, 120);
				bet5.setPosition(840, 155);
			}
		
		//Check mouse is over bet25
		if(Hover25)
			if(!bet25.isOver()) {
				Hover25 = false;
				bet25.setSize(110, 110);
				bet25.setPosition(845, 40);
			}
			else;
		else
			if(bet25.isOver()) {
				Hover25 = true;
				bet25.setSize(120, 120);
				bet25.setPosition(840, 35);
			}
		
		//Check mouse is over bet50
		if(Hover50)
			if(!bet50.isOver()) {
				Hover50 = false;
				bet50.setSize(110, 110);
				bet50.setPosition(970, 160);
			}
			else;
		else
			if(bet50.isOver()) {
				Hover50 = true;
				bet50.setSize(120, 120);
				bet50.setPosition(965, 155);
			}
		
		//Check mouse is over bet100
		if(Hover100)
			if(!bet100.isOver()) {
				Hover100 = false;
				bet100.setSize(110, 110);
				bet100.setPosition(970, 40);
			}
			else;
		else
			if(bet100.isOver()) {
				Hover100 = true;
				bet100.setSize(120, 120);
				bet100.setPosition(965, 35);
			}
}

	public void buildUI(){
		batch = new SpriteBatch();
		backgroundTexture = new Texture("greenfelt.png");
		background = new Sprite(backgroundTexture);
		background.setSize(1100, 800);
		background.setPosition(0, 0);
		menuTexture = new Texture("wood_and_border.png");
		menu = new Sprite(menuTexture);
		menu.setPosition(810, 0);
		balanceTexture = new Texture("balance.png");
		balance = new Sprite(balanceTexture);
		balance.setSize(190, 190);
		balance.setPosition(865, 290);	
		
		/*woodbackground = new ImageButton(new SpriteDrawable(new Sprite(new Texture("wood_and_border.png"))));
		woodbackground.setPosition(810, 0);
		stage.addActor(woodbackground);*/
		
		btnFold = new ImageButton(new SpriteDrawable(new Sprite(new Texture("foldPlain.png"))));
		btnFold.setPosition(830, 500);
		stage.addActor(btnFold);
		
		btnCheck = new ImageButton(new SpriteDrawable(new Sprite(new Texture("checkPlain.png"))));
		btnCheck.setPosition(815, 600);
		stage.addActor(btnCheck);
		
		btnCall = new ImageButton(new SpriteDrawable(new Sprite(new Texture("callPlain.png"))));
		btnCall.setPosition(828, 700);
		stage.addActor(btnCall);
		
		btnSort = new ImageButton(new SpriteDrawable(new Sprite(new Texture("sortPlain.png"))));
		btnSort.setSize(160, 80);
		btnSort.setPosition(10, 0);
		stage.addActor(btnSort);
		
		btnSwap = new ImageButton(new SpriteDrawable(new Sprite(new Texture("swapPlain.png"))));
		btnSwap.setSize(260, 130);
		btnSwap.setPosition(250, -30);
		stage.addActor(btnSwap);
		
		bet5 = new ImageButton(new SpriteDrawable(new Sprite(new Texture("5_chip.png"))));
		bet5.setPosition(845, 160);
		bet5.setSize(110, 110);
		stage.addActor(bet5);
		
		bet25 = new ImageButton(new SpriteDrawable(new Sprite(new Texture("25_chip.png"))));
		bet25.setPosition(845, 40);
		bet25.setSize(110, 110);
		stage.addActor(bet25);
		
		bet50 = new ImageButton(new SpriteDrawable(new Sprite(new Texture("50_chip.png"))));
		bet50.setPosition(970, 160);
		bet50.setSize(110, 110);
		stage.addActor(bet50);
		
		bet100 = new ImageButton(new SpriteDrawable(new Sprite(new Texture("100_chip.png"))));
		bet100.setPosition(970, 40);
		bet100.setSize(110, 110);
		stage.addActor(bet100);
		
		addHoverBtnListeners();
		
		
		// Add listeners
		bet5.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("bet5 pressed");
				bet5.setSize(100, 100);
				bet5.setPosition(850, 165);
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("bet5 released");
				bet5.setSize(120, 120);
				bet5.setPosition(840, 155);
			}
			
		});
		bet25.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("bet25 pressed");
				bet25.setSize(100, 100);
				bet25.setPosition(850, 45);
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("bet25 released");
				bet25.setSize(120, 120);
				bet25.setPosition(840, 35);
			}
			
		});
		bet50.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("bet50 pressed");
				bet50.setSize(100, 100);
				bet50.setPosition(975, 165);
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("bet50 released");
				bet50.setSize(120, 120);
				bet50.setPosition(965, 155);
			}
			
		});
		bet100.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("bet100 pressed");
				bet100.setSize(100, 100);
				bet100.setPosition(975, 45);
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("bet100 released");
				bet100.setSize(120, 120);
				bet100.setPosition(965, 35);
			}
			
		});
		
	}
	
	public void updateCardImg() {
		// Delete existing images
		if(img1 != null) img1.remove(); if(img2 != null) img2.remove(); if(img3 != null) img3.remove();
		if(img4 != null) img4.remove(); if(img5 != null) img5.remove();
		
		// Create button with card image
		img1 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(hand.get(0).getImage()))));
		img2 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(hand.get(1).getImage()))));
		img3 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(hand.get(2).getImage()))));
		img4 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(hand.get(3).getImage()))));
		img5 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(hand.get(4).getImage()))));
		
		// Layout the card buttons
		img1.setSize(120, 240);
		img1.setPosition(30, cardHeight);
		img2.setSize(120, 240);
		img2.setPosition(180, cardHeight);
		img3.setSize(120, 240);
		img3.setPosition(330, cardHeight);
		img4.setSize(120, 240);
		img4.setPosition(480, cardHeight);
		img5.setSize(120, 240);
		img5.setPosition(630, cardHeight);
		
		stage.addActor(img1);
		stage.addActor(img2);
		stage.addActor(img3);
		stage.addActor(img4);
		stage.addActor(img5);
		
		addCardListeners();

	}
	
	public void updateAiCards(){
		// Delete existing images
		if(cardback1 != null) cardback1.remove(); if(cardback2 != null) cardback2.remove(); if(cardback3 != null)cardback3.remove();
		if(cardback4 != null) cardback4.remove(); if(cardback5 != null) cardback5.remove();
		
		//Create ai hand
		aicard1 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(0).getImage()))));
		aicard2 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(1).getImage()))));
		aicard3 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(2).getImage()))));
		aicard4 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(3).getImage()))));
		aicard5 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(4).getImage()))));
				
		cardback1 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("CardBack.png"))));
		cardback2 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("CardBack.png"))));
		cardback3 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("CardBack.png"))));
		cardback4 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("CardBack.png"))));
		cardback5 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("CardBack.png"))));
		
		aicard1.setSize(120, 240);
		aicard1.setPosition(30, 500);
		aicard2.setSize(120, 240);
		aicard2.setPosition(180, 500);
		aicard3.setSize(120, 240);
		aicard3.setPosition(330, 500);
		aicard4.setSize(120, 240);
		aicard4.setPosition(480, 500);
		aicard5.setSize(120, 240);
		aicard5.setPosition(630, 500);
		
		cardback1.setSize(120, 240);
		cardback1.setPosition(30, 500);
		cardback2.setSize(120, 240);
		cardback2.setPosition(180, 500);
		cardback3.setSize(120, 240);
		cardback3.setPosition(330, 500);
		cardback4.setSize(120, 240);
		cardback4.setPosition(480, 500);
		cardback5.setSize(120, 240);
		cardback5.setPosition(630, 500);
		
		stage.addActor(aicard1);
		stage.addActor(aicard2);
		stage.addActor(aicard3);
		stage.addActor(aicard4);
		stage.addActor(aicard5);
		stage.addActor(cardback1);
		stage.addActor(cardback2);
		stage.addActor(cardback3);
		stage.addActor(cardback4);
		stage.addActor(cardback5);
		
		
		cardback1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				int userScore = 0, aiScore = 0;
				System.out.println("Card Back 1 pressed");
				cardback1.addAction(fadeOut(1));
				cardback2.addAction(fadeOut(1));
				cardback3.addAction(fadeOut(1));
				cardback4.addAction(fadeOut(1));
				cardback5.addAction(fadeOut(1));
				hand = sortCards(hand);
				aihand = sortCards(aihand);
				System.out.print("User ");
				userScore = checkHand(hand); // put this into different button listener, for check hand
				System.out.print("AI ");
				aiScore = checkHand(aihand);
				if(userScore > aiScore) {
					System.out.println("You Win!");
				}
				else if(userScore < aiScore) {
					System.out.println("AI Wins :( ");
				}
				else {
					System.out.println("Tie Hand");
					int winner = checkTieGameWinner(userScore);
					if(winner == 1) // user wins
						System.out.println("You Win by high card!");
					else if(winner == 2)
						System.out.println("AI Wins by high card. :( ");
					else
						System.out.println("Both of you win! Cash is split.");
				}
					/*if(hand.get(4).intRank() > aihand.get(4).intRank())
						System.out.println("You Win!");
					else if(hand.get(4).intRank() < aihand.get(4).intRank())
						System.out.println("AI Wins :( ");
					else
						System.out.println("Tie Game");*/
				
				return true;
			}
		});
		
		/*btnComp = new ImageButton(new SpriteDrawable(new Sprite(new Texture("computer_hand.png"))));
		btnUser = new ImageButton(new SpriteDrawable(new Sprite(new Texture("your_hand.png"))));
		
		btnComp.setPosition(30, 740);
		btnUser.setPosition(30, 275);
		
		stage.addActor(btnComp);
		stage.addActor(btnUser);*/
		
	}
	
	public List<Card> drawCards(List<Card> deck) {
		List<Card> hand = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			hand.add(deck.get(0));
			this.deck.remove(0);
		}
		return hand;
	}
	
	public List<Card> shuffle(List<Card> list) {
		Collections.shuffle(list);
		return list;
	}
	
	public boolean swapCards() {
		// Add animation to swap cards
		
		
		/*System.out.print("Before:  ");
		for(int i = 0; i < 5; i++) {
			System.out.print((i+1) + "." + swap[i] + "   ");
		}
		System.out.println();*/
		
		// Take out cards from hand
		if(swap[0]) {
			//img1.addAction();
			deck.add(hand.get(0));
			hand.remove(0);
			hand.add(0, deck.get(0));
			deck.remove(0);
		}
		if(swap[1]) {
			deck.add(hand.get(1));
			hand.remove(1);
			hand.add(1, deck.get(0));
			deck.remove(0);
		}
		if(swap[2]) {
			deck.add(hand.get(2));
			hand.remove(2);
			hand.add(2, deck.get(0));
			deck.remove(0);
		}
		if(swap[3]) {
			deck.add(hand.get(3));
			hand.remove(3);
			hand.add(3, deck.get(0));
			deck.remove(0);
		}
		if(swap[4]) {
			deck.add(hand.get(4));
			hand.remove(4);
			hand.add(4, deck.get(0));
			deck.remove(0);
		}
		java.util.Arrays.fill(swap, false);
		updateCardImg();
		
		// return whether anything was swapped (true) or if nothing happened (false)
		for(int i = 0; i < 5; i++) {
			if(swap[i])
				return true;
		}
		return false;
	}
	
	public int checkHand(List<Card> paramHand) {
		
		List<Card> tempHand = new ArrayList<Card>();// = paramHand;
		for(Card temp : paramHand)
			tempHand.add(temp);
		tempHand = sortCards(tempHand);
		if(tempHand.size() < 5)
			System.out.println("ERROR2: SIZE OF HAND = " + tempHand.size());
		int handStrength = 1;
		String handName = "nothing";
		
		//Detect two of a kind
		int kind = 0;
		for (int i = 0; i < 5; i++)
			for(int j = i + 1; j < 5; j++)
				if (tempHand.get(i).intRank() == tempHand.get(j).intRank())
					kind ++;
		
		if(kind == 6) {
			handName = "Four of a Kind";
			handStrength = 8;
		}
		else if(kind == 4) {
			handName = "Full House";
			handStrength = 7;
		}
		else if(kind == 3) {
			handName = "Three of a Kind";
			handStrength = 4;
		}
		else if(kind == 2) {
			handName = "Two Pairs";
			handStrength = 3;
		}
		else if(kind == 1) {
			handName = "Two of a Kind";
			handStrength = 2;
		}
		
		//Detect straight
		if ((tempHand.get(0).intRank() == (tempHand.get(1).intRank() - 1))
				&& (tempHand.get(1).intRank() == (tempHand.get(2).intRank() - 1))
				&& (tempHand.get(2).intRank() == (tempHand.get(3).intRank() - 1))
				&& (tempHand.get(3).intRank() == (tempHand.get(4).intRank() - 1))){
				handName = "Straight";
				handStrength = 5;
		}
		
		
		//Detect flush
		if ((tempHand.get(0).intSuit() == (tempHand.get(1).intSuit()))
		 && (tempHand.get(1).intSuit() == (tempHand.get(2).intSuit()))
		 && (tempHand.get(2).intSuit() == (tempHand.get(3).intSuit()))
		 && (tempHand.get(3).intSuit() == (tempHand.get(4).intSuit()))){
				handName = "Flush";
				if(handStrength == 5) {
					handName = "Straight Flush";
					handStrength = 9;
					if(tempHand.get(4).intRank() == 14) {
						handName = "Royal Flush";
						handStrength = 10;
					}
						
				}
				else
					handStrength = 6;
		}
		
		System.out.println("Hand = " + handName);
		
		return handStrength;
	}
	
	public int checkTieGameWinner(int handType) {
		int winner = 0;  // initally set to tie
		int userHighCard = 0, aiHighCard = 0;
		if(handType == 2 || handType == 3 || handType == 4 || handType == 7 || handType == 8) {
			// Checks highest card in kinds
			for (int i = 0; i < 5; i++) {
				for(int j = i + 1; j < 5; j++)
					if (hand.get(i).intRank() == hand.get(j).intRank())
						if(hand.get(i).intRank() > userHighCard)
							userHighCard = hand.get(i).intRank();
				for(int j = i + 1; j < 5; j++)
					if (aihand.get(i).intRank() == aihand.get(j).intRank())
							if(aihand.get(i).intRank() > aiHighCard)
						aiHighCard = aihand.get(i).intRank();
			}
		}
		else if(handType < 2 || handType == 5 || handType == 6 || handType > 8) {
			// Checks highest card in both hands
			userHighCard = hand.get(4).intRank();
			aiHighCard = aihand.get(4).intRank();
		}
		
		// Find and return winner
		if(userHighCard > aiHighCard)
			winner = 1;
		else if(userHighCard < aiHighCard)
			winner = 2;
		System.out.println();
		return winner;
	}
	
	public List<Card> sortCards(List<Card> hand) {
		if (hand.isEmpty()) return hand;
		List<Card> sortedHand = new ArrayList<Card>();
		sortedHand.add(hand.get(0));
		hand.remove(0);
		for(Card temp : hand) {
			boolean placed = false;
			Card here = null;
			for(Card sortTemp : sortedHand) {
				if(!placed && (temp.intRank() <= sortTemp.intRank())) {
					if((temp.intRank() == sortTemp.intRank()) && temp.intSuit() > sortTemp.intSuit());
					else {
						here = sortTemp;
						placed = true;
					}
				}
			}
			if(here == null)
				sortedHand.add(temp);
			else
				sortedHand.add(sortedHand.indexOf(here), temp);
			
		}
		//hand = sortedHand;
		System.out.println("SORTED");
		if(sortedHand.size() < 5)
			System.out.println("ERROR: SIZE OF HAND = " + sortedHand.size());
		java.util.Arrays.fill(swap, false);
		return sortedHand;
	}
	
	public void addHoverBtnListeners() {
		btnSort.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("btnSort pressed");
				hand = sortCards(hand);
				aihand = sortCards(aihand);
				updateCardImg();
				updateAiCards();
				return true;	
			}
		});
		btnSwap.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("btnSort pressed");
				swapCards();
				updateCardImg();
				return true;	
			}
		});
		btnFold.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("btnFold pressed");
				
				return true;
			}
		});
		btnCheck.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("btnCheck pressed");
				return true;
			}
		});
		btnCall.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("btnCall pressed");
				return true;
			}
		});
		
	}
	
	public void addCardListeners() {
		img1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 1 pressed");
				if(img1.getY() == cardHeight) {
					img1.addAction(moveTo(img1.getX(), cardHeight+20, (float)0.15));
					swap[0] = true;
				}
				else {
					img1.addAction(moveTo(img1.getX(), cardHeight, (float)0.15));
					swap[0] = false;
				}
				return true;
			}
		});
		img2.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 2 pressed");
				if(img2.getY() == cardHeight) {
					img2.addAction(moveTo(img2.getX(), cardHeight+20, (float)0.15));
					swap[1] = true;
				}
				else {
					img2.addAction(moveTo(img2.getX(), cardHeight, (float)0.15));
					swap[1] = false;
				}
				return true;
			}
		});
		img3.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 3 pressed");
				//img3.addAction(fadeOut(1));  // This is how to do an action
				if(img3.getY() == cardHeight) {
					img3.addAction(moveTo(img3.getX(), cardHeight+20, (float)0.15));
					swap[2] = true;
				}
				else {
					img3.addAction(moveTo(img3.getX(), cardHeight, (float)0.15));
					swap[2] = false;
				}
				return true;
			}
		});
		img4.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 4 pressed");
				if(img4.getY() == cardHeight) {
					img4.addAction(moveTo(img4.getX(), cardHeight+20, (float)0.15));
					swap[3] = true;
				}
				else {
					img4.addAction(moveTo(img4.getX(), cardHeight, (float)0.15));
					swap[3] = false;
				}
				return true;
			}
		});
		img5.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 5 pressed");
				if(img5.getY() == cardHeight) {
					img5.addAction(moveTo(img5.getX(), cardHeight+20, (float)0.15));
					swap[4] = true;
				}
				else {
					img5.addAction(moveTo(img5.getX(), cardHeight, (float)0.15));
					swap[4] = false;
				}
				return true;
			}
		});
	}
}
