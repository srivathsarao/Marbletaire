package com.ashtav.marbletaire.model;

public interface LeadboardsManager {
	public void Login();
	public void LogOut();

	public boolean getSignedIn();

	public void submitScore(int score, String leadboard);

	public boolean getScores();

	public void getScoresData();
}
