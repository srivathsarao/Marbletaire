package com.ashtav.marbletaire.core;

import com.ashtav.marbletaire.model.StyleManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ScoreTextButton extends TextButton {

	public ScoreTextButton(String text) {
		super(text, StyleManager.getTextButtonStyle());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, 0.9f);
	}
}
