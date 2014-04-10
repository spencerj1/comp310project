package com.badlogic.gradletest;

import com.badlogic.PokerGame.PokerGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class PokerLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = config.height + 400;
		config.width = config.width + 1000;
		new LwjglApplication(new PokerGame(), config);
	}
}