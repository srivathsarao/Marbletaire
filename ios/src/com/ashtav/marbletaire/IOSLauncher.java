package com.ashtav.marbletaire;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class IOSLauncher extends IOSApplication.Delegate {
	private IOSAdsManager adsManager;
	
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        
		adsManager = new IOSAdsManager();
		IOSApplication iosApplication = new IOSApplication(new Marbletaire(null, adsManager, null, null), config);
		adsManager.setIosApplication(iosApplication);
        return iosApplication;
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

	@Override
	public boolean didFinishLaunching(UIApplication application, NSDictionary<NSString, ?> launchOptions) {
		boolean returnValue = super.didFinishLaunching(application, launchOptions);
		adsManager.showBannerAd();
		return returnValue;
	}
}