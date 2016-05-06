package com.ashtav.marbletaire.android;

import android.content.Intent;

import com.ashtav.marbletaire.android.GameHelper.GameHelperListener;
import com.ashtav.marbletaire.model.LeadboardsManager;
import com.google.android.gms.games.Games;

public class AndroidLeadboardsManager implements LeadboardsManager {

	private GameHelper gameHelper;
	private AndroidLauncher activity;

	public AndroidLeadboardsManager(AndroidLauncher launcher) {
		this.activity = launcher;
		this.gameHelper = new GameHelper(activity, GameHelper.CLIENT_GAMES);

		GameHelperListener listener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInSucceeded() {
			}

			@Override
			public void onSignInFailed() {
			}

		};
		gameHelper.setup(listener);

	}

	@Override
	public void Login() {
		try {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (final Exception ex) {
		}
	}

	@Override
	public void LogOut() {
		try {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.signOut();
				}
			});
		} catch (final Exception ex) {

		}
	}

	@Override
	public boolean getSignedIn() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void submitScore(int score, String leadboard) {
		if (getSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(), leadboard, score * 1000);
		}
	}

	@Override
	public boolean getScores() {
		if(getSignedIn()) {
			activity.startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient()), 105);
			return true;
		} else {
			Login();
		}
		return false;
	}

	@Override
	public void getScoresData() {
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	public void onStart() {
		gameHelper.onStart(activity);
	}

	public void onStop() {
		gameHelper.onStop();
	}

}
