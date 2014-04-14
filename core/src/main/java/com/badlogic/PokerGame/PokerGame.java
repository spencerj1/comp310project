package com.badlogic.PokerGame;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
	//SpriteBatch batch;
	//Texture img;
	//long time = System.currentTimeMillis();
	ImageButton img1, img2, img3, img4, img5, imgDeck, bet5, bet10, bet25, bet100, cardback1,
	cardback2, cardback3, cardback4, cardback5, aicard1, aicard2, aicard3, aicard4, aicard5;
	ImageButton comp, user;
	Skin skin;
	List<Card> deck, hand, aihand, swap;
	
	@Override
	public void create () {
		
		//skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();
		
		// The stage will respond to input from Gdx (keyboard, mouse, touch, game controller)
		Gdx.input.setInputProcessor(stage);
		deck = Card.deck();
		
		// Draw the cards (should later be put in a buttonListener method)
		deck = shuffle(deck);
		drawCards(deck);
		drawAiCards(deck);
		updateCardImg();
		updateAiCards();
		addCardListeners();
		
	}
		
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, (float)0.5, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		if(quit)
			Gdx.app.exit();
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
		
		//Create ai hand
		aicard1 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(0).getImage()))));
		aicard2 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(1).getImage()))));
		aicard3 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(2).getImage()))));
		aicard4 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(3).getImage()))));
		aicard5 = new ImageButton(new SpriteDrawable(new Sprite(new Texture(aihand.get(4).getImage()))));
		
		// Layout the card buttons
		img1.setSize(120, 240);
		img1.setPosition(30, 30);
		img2.setSize(120, 240);
		img2.setPosition(180, 30);
		img3.setSize(120, 240);
		img3.setPosition(330, 30);
		img4.setSize(120, 240);
		img4.setPosition(480, 30);
		img5.setSize(120, 240);
		img5.setPosition(630, 30);
		
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
		
		stage.addActor(img1);
		stage.addActor(img2);
		stage.addActor(img3);
		stage.addActor(img4);
		stage.addActor(img5);
		
		stage.addActor(aicard1);
		stage.addActor(aicard2);
		stage.addActor(aicard3);
		stage.addActor(aicard4);
		stage.addActor(aicard5);
	}
	
	public void updateAiCards(){
		
		cardback1 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("card_back.png"))));
		cardback2 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("card_back.png"))));
		cardback3 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("card_back.png"))));
		cardback4 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("card_back.png"))));
		cardback5 = new ImageButton(new SpriteDrawable(new Sprite (new Texture("card_back.png"))));
		
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
		
		stage.addActor(cardback1);
		stage.addActor(cardback2);
		stage.addActor(cardback3);
		stage.addActor(cardback4);
		stage.addActor(cardback5);
		
		comp = new ImageButton(new SpriteDrawable(new Sprite(new Texture("computer_hand.png"))));
		user = new ImageButton(new SpriteDrawable(new Sprite(new Texture("your_hand.png"))));
		
		comp.setPosition(30, 740);
		user.setPosition(30, 275);
		
		stage.addActor(comp);
		stage.addActor(user);
		
	}
	
	public void drawCards(List<Card> deck) {
		hand = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			hand.add(deck.get(0));
			this.deck.remove(0);
		}
	}
	
	public void drawAiCards(List<Card> deck) {
		aihand = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			aihand.add(deck.get(0));
			this.deck.remove(0);
		}
	}
	
	public List<Card> shuffle(List<Card> list) {
		Collections.shuffle(list);
		return list;
	}
	
	public String checkHand(List<Card> hand) { // returns a multiplier for payout
		
		hand = sortCards(hand);
		String handname = "nothing";
		boolean Straight = false;
		boolean Flush = false;
		
		//Detect two of a kind
		int kind = 0;
		for (int i = 0; i < 5; i++){
			for(int j = i + 1; j < 5; j++){
				if (hand.get(i).intRank() == hand.get(j).intRank()){
					//handname = "Two of a Kind";
					//break;
					kind ++;
				}
			}
		}
		if(kind == 3)
			handname = "Four of a Kind";
		else if(kind == 2)
			handname = "Three of a Kind";
		else if(kind == 1)
			handname = "Two of a Kind";
			
		//Detect three of a kind
		/*for (int i = 0; i < 5; i++){
			for(int j = i + 1; j < 5; j++){
				if (hand.get(i).intRank() == hand.get(j).intRank()){
					handname = "Two of a Kind";
					System.out.print(handname + handnum);
					break;
				}
			}
		}*/
		
		//Detect four of a kind
		
		//Detect full house
		
		//Detect straight
		if ((hand.get(0).intRank() == (hand.get(1).intRank() - 1))
				&& (hand.get(1).intRank() == (hand.get(2).intRank() - 1))
				&& (hand.get(2).intRank() == (hand.get(3).intRank() - 1))
				&& (hand.get(3).intRank() == (hand.get(4).intRank() - 1))){
				handname = "Straight";
				Straight = true;
		}
		
		
		//Detect flush
		if ((hand.get(0).intSuit() == (hand.get(1).intSuit()))
				&& (hand.get(1).intSuit() == (hand.get(2).intSuit()))
				&& (hand.get(2).intSuit() == (hand.get(3).intSuit()))
				&& (hand.get(3).intSuit() == (hand.get(4).intSuit()))){
				handname = "Flush";
				Flush = true;
		}
		
		
		//Detect straight flush
		if (Flush == true && Straight == true){
				handname = " Straight Flush";
		}
		
		return handname;
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
		hand = sortedHand;
		System.out.println("SORTED");
		return sortedHand;
	}
	
	public void addCardListeners() {
		img1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 1 pressed");
				hand = sortCards(hand); // relocate these later to button for sorting
				updateCardImg();		// this one too
				img1.setColor(Color.BLUE);
				return true;
				
			}
		});
		img2.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 2 pressed");
				img2.addAction(moveTo((float)1, (float)1, (float)1));  // This is how to do an action
				return true;
			}
		});
		img3.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 3 pressed");
				img3.addAction(fadeOut(1));  // This is how to do an action
				return true;
			}
		});
		img4.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 4 pressed");
				img4.addAction(fadeOut(1));  // This is how to do an action
				return true;
			}
		});
		img5.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Card 5 pressed");
				img5.addAction(fadeOut(1));  // This is how to do an action
				return true;
			}
		});
		cardback1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				String userhand;
				String comphand;
				System.out.println("Card Back 1 pressed");
				cardback1.addAction(fadeOut(1));
				cardback2.addAction(fadeOut(1));
				cardback3.addAction(fadeOut(1));
				cardback4.addAction(fadeOut(1));
				cardback5.addAction(fadeOut(1));
				userhand = checkHand(hand);
				System.out.println("Your hand = " + userhand);
				comphand = checkHand(aihand);
				System.out.println("Computer hand = " + comphand);
				return true;
			}
		});
	}
	
	
}
