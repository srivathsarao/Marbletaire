package com.ashtav.marbletaire;

import static com.ashtav.marbletaire.model.GameAssetManager.disposeAssets;

import com.ashtav.marbletaire.core.view.screen.LoadingScreen;
import com.ashtav.marbletaire.model.AdsManager;
import com.ashtav.marbletaire.model.FontManager;
import com.ashtav.marbletaire.model.LeadboardsManager;
import com.ashtav.marbletaire.model.LevelsManager;
import com.ashtav.marbletaire.model.RunnerFactory;
import com.ashtav.marbletaire.model.ShareManager;
import com.badlogic.gdx.Game;

public class Marbletaire extends Game {
	private ShareManager shareManager;
	private AdsManager adsManager;
	private LeadboardsManager leadboardsManager;
	private RunnerFactory runnerFactory;

	public Marbletaire(ShareManager shareManager, AdsManager adsManager, LeadboardsManager  leadboardsManager, RunnerFactory runnerFactory) {
		super();
		this.shareManager = shareManager;
		this.adsManager = adsManager;
		this.leadboardsManager = leadboardsManager;
		this.runnerFactory = runnerFactory;
	}

	@Override
	public void create() {
		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void dispose() {
		disposeAssets();
		LevelsManager.disposeLevels();
		FontManager.disposeFonts();
	}

	public ShareManager getShareManager() {
		return shareManager;
	}

	public AdsManager getAdsManager() {
		return adsManager;
	}

	public LeadboardsManager getLeadboardsManager() {
		return leadboardsManager;
	}

	public RunnerFactory getRunnerFactory() {
		return runnerFactory;
	}
}
