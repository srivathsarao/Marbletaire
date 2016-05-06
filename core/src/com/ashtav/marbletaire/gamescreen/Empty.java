package com.ashtav.marbletaire.gamescreen;

import com.badlogic.gdx.graphics.Texture;

public class Empty extends BoardTableCell {

	public Empty(Texture texture, Integer row, Integer column) {
		super(texture);
		this.setRow(row);
		this.setColumn(column);
	}
}
