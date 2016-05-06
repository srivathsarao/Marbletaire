package com.ashtav.marbletaire.view.exitmenu;

import com.ashtav.marbletaire.Marbletaire;
import com.ashtav.marbletaire.core.AbstractTable;
import com.ashtav.marbletaire.core.view.screen.GameScreen;
import com.ashtav.marbletaire.gamescreen.Marble;
import com.ashtav.marbletaire.model.GameData;
import com.ashtav.marbletaire.model.GameState;
import com.ashtav.marbletaire.model.SerializerEngine;
import com.ashtav.marbletaire.model.StyleManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ExitTable extends AbstractTable {

	public ExitTable(Marbletaire marbletaire) {
		super(marbletaire);
		Label label = new Label("Do you want to exit game?", StyleManager.getLabelStyle());

		TextButton restartButton = new TextButton("Yes", StyleManager.getTextButtonStyle());

		restartButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean val = super.touchDown(event, x, y, pointer, button);
				if (!GameScreen.gameState.equals(GameState.GAME_COMPLETED)) {
					SerializerEngine.serializeGameData(getGameData());
				}
				Gdx.app.exit();
				return val;
			}
		});

		TextButton exitButton = new TextButton("No", StyleManager.getTextButtonStyle());

		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				getGameScreen().disposeOverlay();
				if (GameScreen.gameState.equals(GameState.GAME_COMPLETED)) {
					GameData<Integer, Integer, Marble> newGameData = new GameData<Integer, Integer, Marble>();
					newGameData.setLevel(getGameData().getLevel());
					getMarbletaire().setScreen(new GameScreen(getMarbletaire(), newGameData));
				} else {
					GameScreen.gameState = GameState.GAME_RUNNING;
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		this.add(label).colspan(2);
		this.row();
		this.add(restartButton);
		this.add(exitButton);
	}
}
