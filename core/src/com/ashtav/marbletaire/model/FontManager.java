package com.ashtav.marbletaire.model;

import com.ashtav.marbletaire.model.font.SmartFontGenerator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontManager {
	private static BitmapFont font;
	private static BitmapFont mediumFont;
	private static BitmapFont mediumBoldFont;
	private static BitmapFont smallFont;

	public static void initializeFonts() {
		int mediumSize = Gdx.graphics.getWidth() / 10;
		int normalSize = Gdx.graphics.getWidth() / 16;
		int smallSize = Gdx.graphics.getWidth() / 25;
		
		SmartFontGenerator fontGen = new SmartFontGenerator();
		FileHandle regularFont = Gdx.files.internal("fonts/ClearSans-Regular.ttf");
		FileHandle boldFont = Gdx.files.internal("fonts/ClearSans-Bold.ttf");
		
		font = fontGen.createFont(boldFont, "font", normalSize);
		mediumFont = fontGen.createFont(regularFont, "medium", mediumSize);
		mediumBoldFont = fontGen.createFont(boldFont, "mediumBold", mediumSize);
		smallFont = fontGen.createFont(regularFont, "smallFont", smallSize);
	}
	
	public static void disposeFonts() {

	}

	public static BitmapFont getMediumFont() {
		return mediumFont;
	}

	public static BitmapFont getMediumBoldFont() {
		return mediumBoldFont;
	}

	public static BitmapFont getSmallFont() {
		return smallFont;
	}

	public static BitmapFont getFont() {
		return font;
	}
}
