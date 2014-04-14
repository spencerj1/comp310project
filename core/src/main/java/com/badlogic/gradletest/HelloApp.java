
package com.badlogic.gradletest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class HelloApp extends ApplicationAdapter {
	Stage stage;
//	SpriteBatch batch;
	Texture img;
	ImageButton button;
	
	@Override
	public void create () {
		// All the world's a stage
		stage = new Stage();
		// The stage will respond to input from Gdx (keyboard, mouse, touch, game controller)
		Gdx.input.setInputProcessor(stage);
		
		// Create a button with two images
		button = new ImageButton(
				new SpriteDrawable(new Sprite(new Texture("2_of_clubs.png"))),
				new SpriteDrawable(new Sprite(new Texture("3_of_clubs.png"))));
		
		// Layout that button
		button.setSize(100, 200);
		button.setPosition(20, 20);
		
		// Handle events (aka controller in MVC)
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Hi, down there!");
				// Demonstrate that we can move the button around, too
				button.setPosition(button.getX()+5, button.getY()+5);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Hi, up there!");
			}
			
		});
		
		// The button is on the stage
		stage.addActor(button);
//		batch = new SpriteBatch();
//		img = new Texture(new Pixmap(Gdx.files.internal("2_of_clubs.png")));
//		try {
//			new FreeTypeFontGenerator(Gdx.files.internal("test.fnt"));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		Bullet.init();
		time = System.currentTimeMillis();
	}

	long time;
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, (float)0.5, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 20, 20, 100, 200);
//		batch.end();
		stage.draw();
		if (System.currentTimeMillis() - time < 3000) {
			button.setSize(button.getWidth()+1, button.getHeight()+1);
		}
	}
}
