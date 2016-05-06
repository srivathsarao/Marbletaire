package com.ashtav.marbletaire.model;

import static com.ashtav.marbletaire.model.GameAssetManager.BLUE_MARBLE;
import static com.ashtav.marbletaire.model.GameAssetManager.GREEN_MARBLE;
import static com.ashtav.marbletaire.model.GameAssetManager.ORANGE_MARBLE;
import static com.ashtav.marbletaire.model.GameAssetManager.RANDOM_MARBLE;
import static com.ashtav.marbletaire.model.GameAssetManager.RED_MARBLE;
import static com.ashtav.marbletaire.model.GameAssetManager.VIOLET_MARBLE;
import static com.ashtav.marbletaire.model.GameAssetManager.YELLOW_MARBLE;
import static com.ashtav.marbletaire.model.GameAssetManager.getDefaultColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ColorData {
	private String name;
	private String color;
	
	private static List<ColorData> colorDataList;
	private static Random randomGenerator;
	
	public ColorData(String color, String name) {
		this.color = color;
		this.name = name;
	}

	public static void initializeColorData() {
		colorDataList = new ArrayList<ColorData>();
		
		colorDataList.add(new ColorData(getDefaultColor(), BLUE_MARBLE));
		colorDataList.add(new ColorData("FFFFFF", RANDOM_MARBLE));
		colorDataList.add(new ColorData("AA0000", RED_MARBLE));
		colorDataList.add(new ColorData("FFD42A", YELLOW_MARBLE));
		colorDataList.add(new ColorData("FF6600", ORANGE_MARBLE));
		colorDataList.add(new ColorData("ABC837", GREEN_MARBLE));
		colorDataList.add(new ColorData("AA00D4", VIOLET_MARBLE));
		
		randomGenerator = new Random();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static ColorData getNextColor(ColorData colorData) {
		Integer index = colorDataList.indexOf(colorData);
		if (colorDataList.size() - 1 == index) {
			return colorDataList.get(0);
		} else {
			return colorDataList.get(index + 1);
		}
	}
	
	public static ColorData getColor(String name) {
		for(ColorData colorData : colorDataList) {
			if(colorData.getColor().equals(name)) {
				return colorData;
			}
		}
		return null;
	}
	
	public static Texture getTexture(ColorData colorData) {
		if (RANDOM_MARBLE.equals(colorData.getName())) {
			int index = generateRandom(0, colorDataList.size() - 1);
			ColorData item = colorDataList.get(index);
			return GameAssetManager.getAsset(item.getName(), Texture.class);
		}
		return GameAssetManager.getAsset(colorData.getName(), Texture.class);
	}
	
	private static int generateRandom(int start, int end) {
		int range = end - start + 1;
		int random = start;

		while (true) {
			random = randomGenerator.nextInt(range);
			if(1 == random) {
				continue;
			} else {
				break;
			}
		}
		return random;
	}
	
	public static TextButtonStyle getStyle(ColorData colorData) {
		TextButtonStyle style = StyleManager.getTextButtonStyle();
		style.fontColor = Color.valueOf(colorData.getColor());
		return style;
	}
	
	public static String getText(ColorData colorData) {
		String colorText = colorData.getName().split("\\.")[0];
		char first = Character.toUpperCase(colorText.charAt(0));
		colorText = first + colorText.substring(1);
		return "Color: " + colorText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorData other = (ColorData) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
