package com.ashtav.marbletaire.model;

public interface Runner {
	public void start(Runnable runnable);
	public boolean isAlive();
}
