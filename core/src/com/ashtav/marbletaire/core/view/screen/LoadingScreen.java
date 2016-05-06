package com.ashtav.marbletaire.core.view.screen;

import static com.ashtav.marbletaire.model.GameAssetManager.initializeAssets;
import static com.ashtav.marbletaire.model.GameAssetManager.update;
import aurelienribon.tweenengine.TweenManager;

import com.ashtav.marbletaire.Marbletaire;
import com.ashtav.marbletaire.gamescreen.Marble;
import com.ashtav.marbletaire.model.FontManager;
import com.ashtav.marbletaire.model.GameAssetManager;
import com.ashtav.marbletaire.model.GameData;
import com.ashtav.marbletaire.model.LevelsManager;
import com.ashtav.marbletaire.model.Runner;
import com.ashtav.marbletaire.model.SerializerEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LoadingScreen implements Screen {

	private Stage stage;
	private Color color;
	private Marbletaire marbletaire;
	private Runner fontThread;
	private Image image;

	public static final TweenManager tweenManager = new TweenManager();

	public LoadingScreen(Marbletaire marbletaire) {
		color = Color.GRAY;
		this.marbletaire = marbletaire;
	}

	@Override
	public void render(float delta) {
		if (update() && !fontThread.isAlive()) {
			GameData<Integer, Integer, Marble> gameData = SerializerEngine.deserializeLevel();
			marbletaire.setScreen(new GameScreen(marbletaire, SerializerEngine.deserializeGameData(gameData)));
		}
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		tweenManager.update(Gdx.graphics.getDeltaTime());
		rotateRight();
	}

	private void rotateRight() {
		image.setRotation(image.getRotation() - 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		stage = new Stage();
		fontThread = marbletaire.getRunnerFactory().createRunner();
		fontThread.start(new Runnable() {

			@Override
			public void run() {
				Gdx.app.postRunnable(new Runnable() {

					@Override
					public void run() {
						FontManager.initializeFonts();
					}
				});
				LevelsManager.initializeLevels();
			}
		});
		initializeAssets();

		String subFolder = "";
		if(Gdx.files.internal(GameAssetManager.IMAGE_FOLDER + "/" + Gdx.graphics.getWidth()).exists()) {
			subFolder = "" + Gdx.graphics.getWidth();
		} else {
			subFolder = "720";
		}
		
		image = new Image(new Texture(GameAssetManager.IMAGE_FOLDER + "/" + subFolder + "/" + "loading.png"));
		int newWidth = Gdx.graphics.getWidth() / 5;
		float width = image.getWidth();
		float height = image.getHeight();
		float newHeight = (newWidth * height) / width;

		float posX = (Gdx.graphics.getWidth() / 2) - (newWidth / 2);
		float posY = (Gdx.graphics.getHeight() / 2) - (newHeight / 2);

		image.setWidth(newWidth);
		image.setHeight(newHeight);
		image.setPosition(posX, posY);
		image.setOrigin(newWidth / 2, newHeight / 2);
		stage.addActor(image);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
