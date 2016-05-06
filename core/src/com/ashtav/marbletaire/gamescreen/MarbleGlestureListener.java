package com.ashtav.marbletaire.gamescreen;

import com.ashtav.marbletaire.core.view.screen.GameScreen;
import com.ashtav.marbletaire.model.Direction;
import com.ashtav.marbletaire.model.GameState;
import com.ashtav.marbletaire.model.gamescreen.BoardTable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class MarbleGlestureListener extends ActorGestureListener {
	BoardTable table;

	public MarbleGlestureListener(BoardTable table) {
		this.table = table;
	}

	@Override
	public void fling(InputEvent event, float velocityX, float velocityY, int button) {
		if(GameState.GAME_RUNNING != GameScreen.gameState) {
			return;
		}
		Marble marble = (Marble) event.getListenerActor();
		if (Math.abs(velocityX) > Math.abs(velocityY)) {
			if (velocityX > 0) {
				table.moveMarble(marble, Direction.RIGHT);
			} else {
				table.moveMarble(marble, Direction.LEFT);
			}
		} else {
			if (velocityY > 0) {
				table.moveMarble(marble, Direction.TOP);
			} else {
				table.moveMarble(marble, Direction.BOTTOM);
			}
		}
	}
}
