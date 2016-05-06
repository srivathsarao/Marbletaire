package com.ashtav.marbletaire.android;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.ashtav.marbletaire.model.AdsManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AndroidAdsManager implements AdsManager {
	private RelativeLayout layout;
	private AndroidLauncher marbletaireActivity;
	private static final String BANNER_UNIT_ID = "ca-app-pub-4074445527704469/6424552035";
	private static final String INTERSTITIAL_UNIT_ID = "ca-app-pub-4074445527704469/5107439235";
	private InterstitialAd interstitial;

	public AndroidAdsManager(AndroidLauncher marbletaireActivity, RelativeLayout layout) {
		this.layout = layout;
		this.marbletaireActivity = marbletaireActivity;
		
	    interstitial = new InterstitialAd(marbletaireActivity);
	    interstitial.setAdUnitId(INTERSTITIAL_UNIT_ID);
	    interstitial.loadAd(new AdRequest.Builder().build());
	}

	@Override
	public void showFullScreenAd() {
		marbletaireActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (interstitial.isLoaded()) {
					interstitial.show();
				}
			}
		});
	}

	@Override
	public void showBannerAd() {
		AdView adView = new AdView(marbletaireActivity);
		adView.setAdUnitId(BANNER_UNIT_ID);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.loadAd(new AdRequest.Builder().build());
		adView.setBackgroundColor(Color.BLACK);

		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		layout.addView(adView, adParams);
	}
}
