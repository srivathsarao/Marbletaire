package com.ashtav.marbletaire;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.bindings.admob.GADAdSizeManager;
import org.robovm.bindings.admob.GADBannerView;
import org.robovm.bindings.admob.GADBannerViewDelegateAdapter;
import org.robovm.bindings.admob.GADInterstitial;
import org.robovm.bindings.admob.GADRequest;
import org.robovm.bindings.admob.GADRequestError;

import com.ashtav.marbletaire.model.AdsManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.utils.Logger;

public class IOSAdsManager implements AdsManager {

	private static final Logger log = new Logger(IOSAdsManager.class.getName(), Application.LOG_DEBUG);

	private static final boolean USE_TEST_DEVICES = true;
	private static final String BANNER_UNIT_ID = "ca-app-pub-4074445527704469/6424552035";
	private static final String INTERSTITIAL_UNIT_ID = "ca-app-pub-4074445527704469/6590845637";
	private GADBannerView adview;
	private boolean adsInitialized = false;
	private IOSApplication iosApplication;

	public void setIosApplication(IOSApplication iosApplication) {
		this.iosApplication = iosApplication;
	}

	@Override
	public void showFullScreenAd() {
		GADInterstitial interstitial = new GADInterstitial();
		interstitial.setAdUnitID(INTERSTITIAL_UNIT_ID);
		interstitial.loadRequest(GADRequest.request());
	}

	@Override
	public void showBannerAd() {
		if (!adsInitialized) {
			log.debug("Initalizing ads...");

			adsInitialized = true;

			adview = new GADBannerView(GADAdSizeManager.smartBannerPortrait());
			adview.setAdUnitID(BANNER_UNIT_ID);
			adview.setRootViewController(iosApplication.getUIViewController());
			iosApplication.getUIViewController().getView().addSubview(adview);

			final GADRequest request = GADRequest.request();
			if (USE_TEST_DEVICES) {
				final NSArray<?> testDevices = new NSArray<NSObject>(new NSString(GADRequest.GAD_SIMULATOR_ID));
				request.setTestDevices(testDevices);
				log.debug("Test devices: " + request.getTestDevices());
			}

			adview.setDelegate(new GADBannerViewDelegateAdapter() {
				@Override
				public void didReceiveAd(GADBannerView view) {
					super.didReceiveAd(view);
					log.debug("didReceiveAd");
				}

				@Override
				public void didFailToReceiveAd(GADBannerView view, GADRequestError error) {
					super.didFailToReceiveAd(view, error);
					log.debug("didFailToReceiveAd:" + error);
				}
			});

			adview.loadRequest(request);

			log.debug("Initalizing ads complete.");
		}

		final CGSize screenSize = UIScreen.getMainScreen().getBounds().size();
		double screenWidth = screenSize.width();

		final CGSize adSize = adview.getBounds().size();
		double adWidth = adSize.width();
		double adHeight = adSize.height();

		log.debug(String.format("Showing ad. size[%s, %s]", adWidth, adHeight));

		float bannerWidth = (float) screenWidth;
		float bannerHeight = (float) (bannerWidth / adWidth * adHeight);

		adview.setFrame(new CGRect((screenWidth / 2) - adWidth / 2, 0, bannerWidth, bannerHeight));
	}
}
