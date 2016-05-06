package com.ashtav.marbletaire.view.scoremenu;

import com.ashtav.marbletaire.Marbletaire;
import com.ashtav.marbletaire.core.AbstractTable;
import com.ashtav.marbletaire.core.view.screen.GameScreen;
import com.ashtav.marbletaire.gamescreen.Marble;
import com.ashtav.marbletaire.model.GameData;
import com.ashtav.marbletaire.model.GameState;
import com.ashtav.marbletaire.model.Level;
import com.ashtav.marbletaire.model.ScreenshotManager;
import com.ashtav.marbletaire.model.SerializerEngine;
import com.ashtav.marbletaire.model.StyleManager;
import com.ashtav.marbletaire.view.mainmenu.MainMenuTable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ScoreTable extends AbstractTable {
	
	private static final String SHARE_MESSAGE_COMPLETE = "Can you beat this time, check in into Marbletaire to show your blitz. Try by installing https://play.google.com/store/apps/details?id=com.ashtav.marbletaire.android";
	private static final String SHARE_MESSAGE_INCOMPLETE = "Beat this to be a smart one at Marbletaire. Try by installing https://play.google.com/store/apps/details?id=com.ashtav.marbletaire.android";
	
	public ScoreTable(final Marbletaire marbletaireGame) {
		super(marbletaireGame);
		GameScreen.gameState = GameState.GAME_COMPLETED;

		Label label = new Label("", StyleManager.getBigLabelStyle());

		if (getGameData().size() == 1) {
			label.setText("You Won");
		} else {
			label.setText("You Lost");
		}
		this.add(label).colspan(3);
		this.row();

		TextButton restartButton = new TextButton("Restart Game", StyleManager.getTextButtonStyle());
		this.add(restartButton).colspan(3);

		this.row();
		restartButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Level level = getGameData().getLevel();
				GameData<Integer, Integer, Marble> gameData = new GameData<Integer, Integer, Marble>();
				gameData.setLevel(level);
				getMarbletaire().setScreen(new GameScreen(getMarbletaire(), gameData));
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		TextButton exitButton = new TextButton("Main Menu", StyleManager.getTextButtonStyle());
		this.add(exitButton).colspan(3);
		this.row();
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				getGameScreen().disposeOverlay();
				getGameScreen().showOverlay(new MainMenuTable(getMarbletaire()));
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		TextButton shareButton = new TextButton("Share", StyleManager.getTextButtonStyle());
		this.add(shareButton).colspan(3);
		this.row();
		shareButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				String message = "";
				if(getGameData().size() == 1) {
					message = SHARE_MESSAGE_COMPLETE;
				} else {
					message = SHARE_MESSAGE_INCOMPLETE;					
				}
				getMarbletaire().getShareManager().shareData(ScreenshotManager.getScreenshot(getMarbletaire().getShareManager().getStoragePath()), message);
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		if (getGameData().size() == 1) {
			marbletaireGame.getLeadboardsManager().submitScore((int) getGameData().getDeltaTime(), getGameData().getLevel().getLeaderBoardId());
		}
		SerializerEngine.removeContinuePreferences(getGameData());
	}
}
