package com.ashtav.marbletaire.model;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameAssetManager {
	private static AssetManager assetManager;
	private static Image gameOverlay;

	public static String BLUE_MARBLE = "blue.png";
	public static String RED_MARBLE = "red.png";
	public static String YELLOW_MARBLE = "yellow.png";
	public static String ORANGE_MARBLE = "orange.png";
	public static String GREEN_MARBLE = "green.png";
	public static String VIOLET_MARBLE = "violet.png";
	public static String RANDOM_MARBLE = "random.png";
	
	public static String MENU = "menu.png";
	public static String EMPTY = "empty.png";
	public static String UNDO = "undo.png";
	
	public static String IMAGE_FOLDER = "images";
	private static String IMAGE_SUB_FOLDER = "";
	
	public static String CLICK_SOUND = "sound/click.mp3";
	
	private static Color background;
	private static Color fontColor;
	
	private static String defaultColor;
	
	private static Map<String, String> colorData;

	public static void initializeAssets() {
		assetManager = new AssetManager();

		TextureParameter param = new TextureParameter();
		param.minFilter = TextureFilter.Linear;
		param.magFilter = TextureFilter.Linear;

		if(Gdx.files.internal(IMAGE_FOLDER + "/" + Gdx.graphics.getWidth()).exists()) {
			IMAGE_SUB_FOLDER = "" + Gdx.graphics.getWidth();
		} else {
			IMAGE_SUB_FOLDER = "720";
		}

		addImage(BLUE_MARBLE, param);
		addImage(RED_MARBLE, param);
		addImage(YELLOW_MARBLE, param);
		addImage(ORANGE_MARBLE, param);
		addImage(GREEN_MARBLE, param);
		addImage(VIOLET_MARBLE, param);
		
		addImage(MENU, param);
		addImage(EMPTY, param);
		addImage(UNDO, param);

		assetManager.load(CLICK_SOUND, Sound.class);

		//assetManager.finishLoading();
		
		Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);

		background = Color.valueOf("202020");
		fontColor = Color.valueOf("EEEEEE");
		
		pixmap.setColor(background.r, background.g, background.b, .6f);
		pixmap.fill();
		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		
		gameOverlay = new Image(texture);
		
		defaultColor = "5599FF";

		ColorData.initializeColorData();
	}
	
	public static boolean update() {
		if (assetManager != null && assetManager.update()) {
			return true;
		}
		return false;
	}

	public static Color getBackground() {
		return background;
	}

	public static Color getFontColor() {
		return fontColor;
	}

	public static Image getOverlay() {
		return gameOverlay;
	}

	public static <T> T getAsset(String name, Class<T> classType) {
		if(classType.equals(Texture.class)) {
			String imageFolder = IMAGE_FOLDER + "/" + IMAGE_SUB_FOLDER + "/" + name;
			return assetManager.get(imageFolder, classType);
		}
		return assetManager.get(name, classType);
	}
	
	private static void addImage(String image, TextureParameter textureParam) {
		String imageFolder = IMAGE_FOLDER + "/" + IMAGE_SUB_FOLDER + "/" + image;
		assetManager.load(imageFolder, Texture.class, textureParam);
	}

	public static void disposeAssets() {
		assetManager.clear();
	}
	
	public static Map<String, String> getColorDataMap() {
		return null;
	}

	public static String getDefaultColor() {
		return defaultColor;
	}
	
	protected Map<String, String> getColorData() {
		return colorData;
	}
}
