package com.ashtav.marbletaire.view.mainmenu;

import static com.ashtav.marbletaire.model.GameAssetManager.CLICK_SOUND;
import static com.ashtav.marbletaire.model.GameAssetManager.getAsset;

import java.util.Map.Entry;

import com.ashtav.marbletaire.Marbletaire;
import com.ashtav.marbletaire.core.AbstractTable;
import com.ashtav.marbletaire.core.view.screen.GameScreen;
import com.ashtav.marbletaire.gamescreen.Marble;
import com.ashtav.marbletaire.model.ColorData;
import com.ashtav.marbletaire.model.GameData;
import com.ashtav.marbletaire.model.GameData.GameKeys;
import com.ashtav.marbletaire.model.GameState;
import com.ashtav.marbletaire.model.Level;
import com.ashtav.marbletaire.model.LevelsManager;
import com.ashtav.marbletaire.model.SerializerEngine;
import com.ashtav.marbletaire.model.StyleManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenuTable extends AbstractTable {
	private ColorData colorData;

	public MainMenuTable(Marbletaire marbletaireGame) {
		super(marbletaireGame);

		Label label = new Label("MARBLETAIRE", StyleManager.getTitleStyle());
		this.add(label).padBottom(30);
		this.row();
		
		if(GameScreen.gameState.equals(GameState.GAME_PAUSED)) {
			TextButton continueButton = new TextButton("Continue", StyleManager.getTextButtonStyle());
			continueButton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					GameScreen.gameState = GameState.GAME_RUNNING;
					getGameScreen().disposeOverlay();
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			this.add(continueButton);
			this.row();
		}

		TextButton newGameButton = new TextButton("New Game", StyleManager.getTextButtonStyle());
		newGameButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				GameData<Integer, Integer, Marble> newGameData = new GameData<Integer, Integer, Marble>();
				newGameData.setLevel(getGameData().getLevel());
				getMarbletaire().setScreen(new GameScreen(getMarbletaire(), newGameData));
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		TextButton leadboardButton = new TextButton("Leadboard", StyleManager.getTextButtonStyle());
		leadboardButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				getMarbletaire().getLeadboardsManager().getScores();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		TextButton rateAppButton = new TextButton("Rate App", StyleManager.getTextButtonStyle());
		rateAppButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.net.openURI("market://details?id=com.ashtav.marbletaire.android");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		final TextButton levelButton = new TextButton("Board: " + getGameData().getLevel().getMessage(), StyleManager.getTextButtonStyle());
		levelButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Level nextLevel = LevelsManager.getNextLevel(getGameData().getLevel().getNumber() - 1);
				changeBoard(nextLevel);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		final TextButton soundButton = new TextButton("Sound: " + (GameScreen.soundEnabled ? "On" : "Off"), StyleManager.getTextButtonStyle());
		soundButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				GameScreen.soundEnabled = !GameScreen.soundEnabled;
				SerializerEngine.setSoundEnabled(GameScreen.soundEnabled);
				if (GameScreen.soundEnabled) {
					getAsset(CLICK_SOUND, Sound.class).play();
				}
				soundButton.setText("Sound: " + (GameScreen.soundEnabled ? "On" : "Off"));
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		colorData = ColorData.getColor(SerializerEngine.getColor());

		final TextButton changeColorButton = new TextButton(ColorData.getText(colorData), ColorData.getStyle(colorData));
		changeColorButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				colorData = ColorData.getNextColor(colorData);
				changeColorButton.setText(ColorData.getText(colorData));
				changeColorButton.setStyle(ColorData.getStyle(colorData));
				SerializerEngine.setColor(colorData.getColor());
				for(Entry<GameKeys<Integer, Integer>, Marble> entry : getGameData().entrySet()) {
					entry.getValue().setDrawable(new TextureRegionDrawable(new TextureRegion(ColorData.getTexture(colorData))));
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		this.add(newGameButton);
		this.row();
		this.add(leadboardButton);
		this.row();
		this.add(rateAppButton);
		this.row();
		this.add(soundButton);
		this.row();
		this.add(levelButton);
		this.row();
		this.add(changeColorButton);
		this.row();
	}
	
	private void changeBoard(Level level) {
		if(GameScreen.gameState.equals(GameState.GAME_PAUSED)) {
			SerializerEngine.serializeGameData(getGameData());			
		} else {
			SerializerEngine.removeContinuePreferences(getGameData());
		}
		GameData<Integer, Integer, Marble> newGameData = new GameData<Integer, Integer, Marble>();
		newGameData.setLevel(level);
		SerializerEngine.serializeLevel(newGameData);
		SerializerEngine.deserializeGameData(newGameData);
		getMarbletaire().setScreen(new GameScreen(getMarbletaire(), newGameData, true));
	}
	
	
}
