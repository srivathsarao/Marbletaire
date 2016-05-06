package com.ashtav.marbletaire.gamescreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BoardTableCell extends Image {
	private Integer row;
	private Integer column;
	protected Texture texture;

	public BoardTableCell(Texture texture) {
		super(texture);
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}
}
