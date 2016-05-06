package com.ashtav.marbletaire.model;

import java.util.Map.Entry;

import com.ashtav.marbletaire.gamescreen.Marble;
import com.ashtav.marbletaire.model.GameData.GameKeys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SerializerEngine {

	public static void serializeLevel(GameData<Integer, Integer, Marble> gameData) {
		Preferences prefs = Gdx.app.getPreferences("levelPref");
		prefs.putInteger("level", gameData.getLevel().getNumber());
		prefs.flush();
	}

	public static void serializeGameData(GameData<Integer, Integer, Marble> gameData) {
		Preferences prefs = Gdx.app.getPreferences("levelPref" + gameData.getLevel().getNumber());
		prefs.putInteger("size", gameData.entrySet().size());
		prefs.putFloat("timeSpent", gameData.getDeltaTime());

		Integer i = 0;
		for (Entry<GameKeys<Integer, Integer>, Marble> entry : gameData.entrySet()) {
			prefs.putInteger("row" + i, entry.getKey().getKeyOne());
			prefs.putInteger("column" + i, entry.getKey().getKeyTwo());
			i++;
		}
		prefs.flush();
	}

	public static GameData<Integer, Integer, Marble> deserializeGameData(GameData<Integer, Integer, Marble> gameData) {
		Preferences prefs = Gdx.app.getPreferences("levelPref" + gameData.getLevel().getNumber());
		float deltaTime = prefs.getFloat("timeSpent");
		Integer size = prefs.getInteger("size");
		if (size == null || size == gameData.getLevel().numberOfMarbles()) {
			return gameData;
		}
		gameData.setDeltaTime(deltaTime);

		for (Integer i = 0; i < size; i++) {
			Integer keyOne = prefs.getInteger("row" + i);
			Integer keyTwo = prefs.getInteger("column" + i);
			gameData.put(keyOne, keyTwo, null);
		}
		return gameData;
	}

	public static void removeContinuePreferences(GameData<Integer, Integer, Marble> gameData) {
		Preferences prefs = Gdx.app.getPreferences("levelPref" + gameData.getLevel().getNumber());
		prefs.clear();
		prefs.flush();
	}

	public static GameData<Integer, Integer, Marble> deserializeLevel() {
		Preferences prefs = Gdx.app.getPreferences("levelPref");
		Integer levelInt = prefs.getInteger("level");
		if (levelInt == null || levelInt == 0) {
			GameData<Integer, Integer, Marble> marble = new GameData<Integer, Integer, Marble>();
			marble.setLevel(LevelsManager.getLevel(0));
			return marble;
		}
		GameData<Integer, Integer, Marble> gameData = new GameData<Integer, Integer, Marble>();
		gameData.setLevel(LevelsManager.getLevel(levelInt - 1));
		return gameData;
	}
	
	public static String getColor() {
		Preferences prefs = Gdx.app.getPreferences("colorPref");
		String color = prefs.getString("color");
		if (color == null || color.trim().isEmpty()) {
			color = GameAssetManager.getDefaultColor();
		}
		prefs.putString("color", color);
		return color;
	}

	public static void setColor(String color) {
		Preferences prefs = Gdx.app.getPreferences("colorPref");
		prefs.putString("color", color);
		prefs.flush();
	}
	
	public static Boolean isSoundEnabled() {
		Preferences prefs = Gdx.app.getPreferences("soundPref");
		String sound = prefs.getString("sound");
		if (sound == null || sound.trim().isEmpty()) {
			return true;
		}
		prefs.putString("sound", sound);
		return Boolean.valueOf(sound);
	}

	public static void setSoundEnabled(Boolean sound) {
		Preferences prefs = Gdx.app.getPreferences("soundPref");
		prefs.putString("sound", sound.toString());
		prefs.flush();
	}
}
