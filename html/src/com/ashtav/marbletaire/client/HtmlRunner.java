package com.ashtav.marbletaire.client;

import com.ashtav.marbletaire.model.Runner;

public class HtmlRunner implements Runner {
	private HtmlTimer timer;

	@Override
	public void start(Runnable runnable) {
		timer = new HtmlTimer(runnable);
		timer.schedule(0);
	}

	@Override
	public boolean isAlive() {
		if(timer == null) {
			return false;
		}
		return timer.isRunning();
	}
}
