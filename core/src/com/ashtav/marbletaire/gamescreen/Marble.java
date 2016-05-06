package com.ashtav.marbletaire.gamescreen;

import com.badlogic.gdx.graphics.Texture;

public class Marble extends BoardTableCell {

	public Marble(Texture texture, Integer row, Integer column) {
		super(texture);
		this.setRow(row);
		this.setColumn(column);
	}
}
