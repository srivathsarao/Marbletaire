package com.ashtav.marbletaire.android;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.ashtav.marbletaire.Marbletaire;
import com.badlogic.gdx.backends.android.AndroidApplication;

public class AndroidLauncher extends AndroidApplication {
	private AndroidLeadboardsManager leadboardsManager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout layout = new RelativeLayout(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		AndroidShareManager shareManager = new AndroidShareManager(this);
		AndroidAdsManager adsManager = new AndroidAdsManager(this, layout);
		leadboardsManager = new AndroidLeadboardsManager(this);

		View gameView = initializeForView(new Marbletaire(shareManager, adsManager, leadboardsManager, new AndroidRunnerFactory()));
		layout.addView(gameView);
		adsManager.showBannerAd();
		setContentView(layout);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		leadboardsManager.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onStart() {
		super.onStart();
		leadboardsManager.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		leadboardsManager.onStop();
	}
	
	
}
