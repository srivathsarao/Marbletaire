package com.ashtav.marbletaire.model;

import static com.ashtav.marbletaire.model.GameAssetManager.getFontColor;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class StyleManager {
	public static LabelStyle getLabelStyle() {
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = FontManager.getFont();
		labelStyle.fontColor = getFontColor();
		return labelStyle;
	}
	
	public static TextButtonStyle getTextButtonStyle() {
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = FontManager.getFont();
		textButtonStyle.fontColor = getFontColor();
		return textButtonStyle;
	}
	
	public static LabelStyle getBigLabelStyle() {
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = FontManager.getMediumBoldFont();
		labelStyle.fontColor = getFontColor();
		return labelStyle;
	}

	public static TextButtonStyle getBigLabelButtonStyle() {
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = FontManager.getMediumFont();
		textButtonStyle.fontColor = getFontColor();
		return textButtonStyle;
	}

	public static LabelStyle getTitleStyle() {
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = FontManager.getMediumBoldFont();
		labelStyle.fontColor = getFontColor();
		return labelStyle;
	}
	
	public static LabelStyle getSmallLabelStyle() {
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = FontManager.getSmallFont();
		labelStyle.fontColor = getFontColor();
		return labelStyle;
	}
}
