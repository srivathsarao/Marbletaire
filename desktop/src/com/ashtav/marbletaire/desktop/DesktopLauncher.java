package com.ashtav.marbletaire.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ashtav.marbletaire.Marbletaire;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 800;
		new LwjglApplication(new Marbletaire(null, null, new DesktopLeadboardsManager(), new DesktopRunnerFactory()), config);
	}
}
