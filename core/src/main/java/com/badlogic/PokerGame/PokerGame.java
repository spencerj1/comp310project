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
	ImageButton img1, img2, img3, img4, img5, imgDeck;
	List<Card> deck, hand, swap;
	
	@Override
	public void create () {
		stage = new Stage();
		
		// The stage will respond to input from Gdx (keyboard, mouse, touch, game controller)
		Gdx.input.setInputProcessor(stage);
		deck = Card.deck();
		// Draw the cards (should later be put in a buttonListener method)
		deck = shuffle(deck);
		drawCards(deck);
		updateCardImg();
		
//		batch = new SpriteBatch();
//		img = new Texture(new Pixmap(Gdx.files.internal("2_of_clubs.png")));
//		try {
//			new FreeTypeFontGenerator(Gdx.files.internal("test.fnt"));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		Bullet.init();
	}
		
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, (float)0.5, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 20, 20, 100, 200);
//		batch.end();
		stage.act();
		stage.draw();
		//if (System.currentTimeMillis() - time < 3000) { // This method enlarges a card
		//	img1.setSize(img1.getWidth()+1, img1.getHeight()+1);
		//}
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
			
		// Layout the card buttons
		img1.setSize(150, 300);
		img1.setPosition(30, 30);
		img2.setSize(150, 300);
		img2.setPosition(210, 30);
		img3.setSize(150, 300);
		img3.setPosition(390, 30);
		img4.setSize(150, 300);
		img4.setPosition(570, 30);
		img5.setSize(150, 300);
		img5.setPosition(750, 30);
		
		stage.addActor(img1);
		stage.addActor(img2);
		stage.addActor(img3);
		stage.addActor(img4);
		stage.addActor(img5);
		
		addCardListeners();
	}
	
	public void drawCards(List<Card> deck) {
		hand = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			hand.add(deck.get(0));
			this.deck.remove(0);
		}
	}
	
	public List<Card> shuffle(List<Card> list) {
		Collections.shuffle(list);
		return list;
	}
	
	public int checkHand(List<Card> hand) { // returns a multiplier for payout
		int mult = 0;
		// Sort Cards
		
		// Find like kinds
		
		//for(int i = 0; i < 5; i++) {
		//	int rank = hand.get(i).intRank();
		//	for(int j = i; j < 5; j++) {
		//		if();
		//	}
		//	kinds = tempKinds;
		//}
		
		/*
		 *  ROYAL FLUSH = 10
			STRAIGHT FLUSH = 9
			FOUR OF A KIND = 8
		 	FULL HOUSE = 7
		 	FLUSH = 6
		 	STRAIGHT = 5
		 	THREE OF A KIND = 4
		 	TWO PAIRS = 3
		 	ONE PAIR = 2
		 	HIGH CARD = 1
		 */
		
		return mult;
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
	}
}