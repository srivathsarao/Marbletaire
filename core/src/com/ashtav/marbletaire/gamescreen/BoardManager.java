package com.ashtav.marbletaire.gamescreen;

import static com.ashtav.marbletaire.model.GameAssetManager.EMPTY;
import static com.ashtav.marbletaire.model.GameAssetManager.UNDO;
import static com.ashtav.marbletaire.model.GameAssetManager.getAsset;

import java.util.ArrayList;
import java.util.List;

import com.ashtav.marbletaire.Marbletaire;
import com.ashtav.marbletaire.core.view.screen.GameScreen;
import com.ashtav.marbletaire.model.ColorData;
import com.ashtav.marbletaire.model.GameAssetManager;
import com.ashtav.marbletaire.model.GameData;
import com.ashtav.marbletaire.model.GameData.GameKeys;
import com.ashtav.marbletaire.model.GameState;
import com.ashtav.marbletaire.model.Level;
import com.ashtav.marbletaire.model.SerializerEngine;
import com.ashtav.marbletaire.model.StyleManager;
import com.ashtav.marbletaire.model.UndoBuffer;
import com.ashtav.marbletaire.model.UndoBuffer.UndoItem;
import com.ashtav.marbletaire.model.gamescreen.BoardTable;
import com.ashtav.marbletaire.view.mainmenu.MainMenuTable;
import com.ashtav.marbletaire.view.scoremenu.ScoreTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BoardManager {
	private BoardTable table;
	private Stage stage;
	private Label scoreLabel;
	private Table menuTable;
	private Label timeElapsedLabel;
	private float adHeight;
	private float prevDeltaTime;
	private Marbletaire marbletaire;
	private MarbleGlestureListener listener;
	private UndoBuffer<UndoItem> undoBuffer;
	private GameScreen gameScreen;

	public BoardManager(Stage stage, Marbletaire marbletaireGame) {
		this.gameScreen = (GameScreen) marbletaireGame.getScreen();
		this.stage = stage;
		this.adHeight = (50f * Gdx.graphics.getDensity()) * 2f;
		this.marbletaire = marbletaireGame;
		this.undoBuffer = new UndoBuffer<UndoItem>(1);

		showMenu();
		this.table = new BoardTable(this, undoBuffer, stage, getMarbleSize(), getLeft(), getTop());
	}

	private void showMenu() {
		Button button = new Button(new TextureRegionDrawable(new TextureRegion(GameAssetManager.getAsset(GameAssetManager.MENU, Texture.class))));
		button.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(!GameScreen.gameState.equals(GameState.GAME_RUNNING)) {
					return false;
				}
				GameScreen.gameState = GameState.GAME_PAUSED;
				showMenuScreen();
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		Button undoButton = new Button(new TextureRegionDrawable(new TextureRegion(getAsset(UNDO, Texture.class))));
		undoButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(!GameScreen.gameState.equals(GameState.GAME_RUNNING)) {
					return false;
				}
				ColorData colorData = ColorData.getColor(SerializerEngine.getColor());
				table.undoMove(colorData);
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		timeElapsedLabel = new Label(getGameData().getTimeSpent(), StyleManager.getSmallLabelStyle());
		scoreLabel = new Label("", StyleManager.getLabelStyle());

		Table subTable = new Table();
		subTable.add(scoreLabel).right();
		subTable.row();
		subTable.add(timeElapsedLabel).right();

		menuTable = new Table();
		menuTable.add(button).left().top().minSize(Gdx.graphics.getWidth() / 8).maxSize(Gdx.graphics.getWidth() / 8).padLeft(Gdx.graphics.getWidth() / 20);
		menuTable.add(undoButton).left().top().minSize(Gdx.graphics.getWidth() / 8).maxSize(Gdx.graphics.getWidth() / 8).padLeft(Gdx.graphics.getWidth() / 20);
		menuTable.add().left().top().expandX();
		menuTable.add(subTable).right().top().padRight(Gdx.graphics.getWidth() / 20).setWidgetHeight(5);

		menuTable.left().top();
		menuTable.setFillParent(true);
		stage.addActor(menuTable);
	}

	public void createBoard() {
		listener = new MarbleGlestureListener(table);
		
		ColorData colorData = ColorData.getColor(SerializerEngine.getColor());

		for (int i = 0; i < getGameData().getRows(); i++) {
			for (int j = 0; j < getGameData().getColumns(); j++) {
				if (getGameData().getLayoutElementType(i, j) == 1 || getGameData().getLayoutElementType(i, j) == 0) {
					Empty empty = new Empty(getAsset(EMPTY, Texture.class), i, j);
					table.add(empty, i, j);
				}
			}
		}

		for (int i = 0; i < getGameData().getRows(); i++) {
			for (int j = 0; j < getGameData().getColumns(); j++) {
				if (getGameData().getLayoutElementType(i, j) == 1) {
					createMarble(colorData, i, j);
					getGameData().setLeft(getGameData().getLeft() + 1);
				}
			}
		}
		updateScore();

		menuTable.localToStageCoordinates(new Vector2(0, 0));
		stage.addActor(table.getGroup());
	}

	public void continueBoard() {
		listener = new MarbleGlestureListener(table);
		
		ColorData colorData = ColorData.getColor(SerializerEngine.getColor());

		for (int i = 0; i < getGameData().getRows(); i++) {
			for (int j = 0; j < getGameData().getColumns(); j++) {
				if (getGameData().getLayoutElementType(i, j) == 1 || getGameData().getLayoutElementType(i, j) == 0) {
					Empty empty = new Empty(getAsset(EMPTY, Texture.class), i, j);
					table.add(empty, i, j);
				}
			}
		}

		List<GameKeys<Integer, Integer>> keysList = new ArrayList<GameKeys<Integer, Integer>>(getGameData().keySet());
		for (GameKeys<Integer, Integer> key : keysList) {
			getGameData().setLeft(getGameData().getLeft() + 1);
			createMarble(colorData, key.getKeyOne(), key.getKeyTwo());
		}

		menuTable.localToStageCoordinates(new Vector2(0, 0));
		stage.addActor(table.getGroup());
		updateScore();
		
		if (!table.movesPossible()) {
			stage.clear();
			Level level = getGameData().getLevel();
			setGameData(new GameData<Integer, Integer, Marble>());
			getGameData().setLevel(level);
			showMenu();
			createBoard();
		}
	}
	
	public void createMarble(ColorData colorData, Integer row, Integer column) {
		Marble marble = new Marble(ColorData.getTexture(colorData), row, column);
		marble.addListener(listener);
		table.add(marble, row, column);
	}

	public void updateScore() {
		scoreLabel.setText("Left: " + getGameData().getLeft());
	}

	public void updateTime(float delta) {
		if(GameScreen.gameState.equals(GameState.GAME_RUNNING)) {
			getGameData().setDeltaTime(getGameData().getDeltaTime() + delta);
			if ((prevDeltaTime + 1) < getGameData().getDeltaTime()) {
				timeElapsedLabel.setText(getGameData().getTimeSpent());
				prevDeltaTime = getGameData().getDeltaTime();
			}
		}
	}

	private Integer getMarbleSize() {
		Integer height = getHeight();
		Integer width = Gdx.graphics.getWidth();

		if ((height / getGameData().getRows()) < (width / getGameData().getColumns())) {
			return height / getGameData().getRows();
		} else {
			return width / getGameData().getColumns();
		}
	}

	private Integer getTop() {
		Integer freeRegionHeight = getHeight();
		float top = ((freeRegionHeight / 2) + getMarbleSize() * (getGameData().getRows() / 2)) + adHeight;
		return (int) top;
	}
	
	private Integer getHeight() {
		return (int) (Gdx.graphics.getHeight() - (Gdx.graphics.getWidth() / 8 + adHeight));
	}
	
	private Integer getLeft() {
		Integer marbleSize = getMarbleSize();
		Integer halfBoardSize = (marbleSize * getGameData().getColumns()) / 2;
		return ((Gdx.graphics.getWidth() / 2) - halfBoardSize);
	}

	public void showScoreScreen() {
		if(showAd()) {
			this.marbletaire.getAdsManager().showFullScreenAd();
		}
		gameScreen.showOverlay(new ScoreTable(marbletaire));
	}
	
	public void showMenuScreen() {
		gameScreen.showOverlay(new MainMenuTable(marbletaire));
	}
	
	private boolean showAd() {
		Integer upper = 5;
		Integer lower = 1;
		int random = (int) ((Math.random() * (upper - lower)) + lower);
		if(random == 3) {
			return true;
		}
		return false;
	}
	
	public GameData<Integer, Integer, Marble> getGameData() {
		return gameScreen.getGameData();
	}
	
	public void setGameData(GameData<Integer, Integer, Marble> gameData) {
		gameScreen.setGameData(gameData);
	}
}