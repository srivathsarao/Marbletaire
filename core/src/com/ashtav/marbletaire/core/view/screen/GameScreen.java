package com.ashtav.marbletaire.core.view.screen;

import static com.ashtav.marbletaire.model.GameAssetManager.getBackground;
import static com.ashtav.marbletaire.model.GameAssetManager.getOverlay;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Cubic;

import com.ashtav.marbletaire.Marbletaire;
import com.ashtav.marbletaire.core.AbstractTable;
import com.ashtav.marbletaire.gamescreen.ActorAccessor;
import com.ashtav.marbletaire.gamescreen.BoardManager;
import com.ashtav.marbletaire.gamescreen.Marble;
import com.ashtav.marbletaire.model.GameData;
import com.ashtav.marbletaire.model.GameState;
import com.ashtav.marbletaire.model.SerializerEngine;
import com.ashtav.marbletaire.model.gamescreen.BoardTable;
import com.ashtav.marbletaire.view.exitmenu.ExitTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameScreen implements Screen {
	public static GameState gameState;
	public static Boolean soundEnabled;

	private BoardManager manager;
	private GameData<Integer, Integer, Marble> gameData;
	private Marbletaire marbletaire;
	private Stage stage;
	private AbstractTable table;
	private Image overlay;
	private boolean showPauseScreen;

	public GameScreen(Marbletaire marbletaire, GameData<Integer, Integer, Marble> gameData) {
		this.gameData = gameData;
		this.marbletaire = marbletaire;
		GameScreen.gameState = GameState.GAME_RUNNING;
		GameScreen.soundEnabled = SerializerEngine.isSoundEnabled();
		SerializerEngine.removeContinuePreferences(gameData);
		Tween.registerAccessor(Image.class, new ActorAccessor());
	}

	public GameScreen(Marbletaire marbletaire, GameData<Integer, Integer, Marble> gameData, boolean showPauseScreen) {
		this(marbletaire, gameData);
		this.showPauseScreen = showPauseScreen;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(getBackground().r, getBackground().g, getBackground().b, getBackground().a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		manager.updateTime(delta);
		BoardTable.tweenManager.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		InputMultiplexer multiplexer = new InputMultiplexer(stage, backProcessor);
		Gdx.input.setInputProcessor(multiplexer);

		manager = new BoardManager(getStage(), marbletaire);
		if (!gameData.isNewGame()) {
			manager.continueBoard();
		} else {
			manager.createBoard();
		}

		if (showPauseScreen) {
			showMenuScreen();
		}
	}

	public void showMenuScreen() {
		if (!gameData.isNewGame()) {
			GameScreen.gameState = GameState.GAME_PAUSED;
		} else {
			GameScreen.gameState = GameState.GAME_COMPLETED;
		}
		manager.showMenuScreen();
	}

	@Override
	public void dispose() {
		this.stage.dispose();
	}

	public GameData<Integer, Integer, Marble> getGameData() {
		return gameData;
	}

	public void setGameData(GameData<Integer, Integer, Marble> gameData) {
		this.gameData = gameData;
	}

	public Stage getStage() {
		return stage;
	}

	InputProcessor backProcessor = new InputAdapter() {
		@Override
		public boolean keyDown(int keycode) {
			GameScreen instance = GameScreen.this;
			if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK)) {
				if (instance instanceof GameScreen) {
					if (!gameState.equals(GameState.GAME_RUNNING)) {
						table.remove();
						showOverlay(new ExitTable(marbletaire));
					} else {
						gameState = GameState.GAME_PAUSED;
						showOverlay(new ExitTable(marbletaire));
					}
				}
			}
			return false;
		}
	};

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public void showOverlay(final AbstractTable table) {
		overlay = getOverlay();
		overlay.setWidth(0);
		stage.addActor(overlay);

		Tween.to(overlay, ActorAccessor.SIZE, 0.5f).target(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()).ease(Cubic.INOUT).setCallback(new TweenCallback() {
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				table.setFillParent(true);
				stage.addActor(table);
				GameScreen.this.table = table;
			}
		}).start(BoardTable.tweenManager);
	}

	public void disposeOverlay() {
		if (this.table != null) {
			this.table.remove();
		}
		if (this.overlay != null) {
			this.overlay.remove();
		}
	}
}
