package com.ashtav.marbletaire.client;

import com.google.gwt.user.client.Timer;

public class HtmlTimer extends Timer {
	private Runnable runnable;

	public HtmlTimer(Runnable runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public void run() {
		runnable.run();
	}
}
