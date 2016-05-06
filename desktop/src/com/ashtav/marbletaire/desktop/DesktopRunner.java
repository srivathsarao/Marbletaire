package com.ashtav.marbletaire.desktop;

import com.ashtav.marbletaire.model.Runner;

public class DesktopRunner implements Runner {
	private Thread thread;

	@Override
	public void start(Runnable runnable) {
		thread = new Thread(runnable);
		thread.start();
	}

	@Override
	public boolean isAlive() {
		if (thread != null) {
			return thread.isAlive();
		}
		return false;
	}
}
