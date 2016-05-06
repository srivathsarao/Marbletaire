package com.ashtav.marbletaire.android;

import com.ashtav.marbletaire.model.Runner;

public class AndroidRunner implements Runner {
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
