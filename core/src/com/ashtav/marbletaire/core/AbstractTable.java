package com.ashtav.marbletaire.core;

import com.ashtav.marbletaire.Marbletaire;
import com.ashtav.marbletaire.core.view.screen.GameScreen;
import com.ashtav.marbletaire.gamescreen.Marble;
import com.ashtav.marbletaire.model.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.tablelayout.Cell;

public abstract class AbstractTable extends Table {

	private Marbletaire marbletaire;
	private GameScreen gameScreen;
	private GameData<Integer, Integer, Marble> gameData;

	public AbstractTable(Marbletaire marbletaire) {
		super();
		this.marbletaire = marbletaire; 
		gameScreen = (GameScreen) marbletaire.getScreen();
		gameData = gameScreen.getGameData();
		getGameScreen().disposeOverlay();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cell add(Actor actor) {
		Cell cell = super.add(actor);
		cell.pad(Gdx.graphics.getWidth() / 100);
		cell.padBottom(Gdx.graphics.getWidth() / 30);
		return cell;
	}

	public Marbletaire getMarbletaire() {
		return marbletaire;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public GameData<Integer, Integer, Marble> getGameData() {
		return gameData;
	}

	public void setGameData(GameData<Integer, Integer, Marble> gameData) {
		this.gameData = gameData;
	}
}
