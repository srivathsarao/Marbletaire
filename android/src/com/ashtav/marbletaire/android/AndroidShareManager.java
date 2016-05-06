package com.ashtav.marbletaire.android;

import java.io.File;
import java.util.Date;

import android.content.Intent;
import android.net.Uri;

import com.ashtav.marbletaire.model.ShareManager;

public class AndroidShareManager implements ShareManager {

	private AndroidLauncher marbletaireActivity;

	public AndroidShareManager(AndroidLauncher marbletaireActivity) {
		this.marbletaireActivity = marbletaireActivity;
	}

	@Override
	public void shareData(String path, String data) {
		File file = new File(path);
	    Intent share = new Intent();
	    share.setAction(Intent.ACTION_SEND);
	    share.putExtra(Intent.EXTRA_TEXT, data);
	    share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
	    share.setType("image/png");
	    share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
	    marbletaireActivity.startActivity(Intent.createChooser(share, "Share the Achievement!"));
	}

	@Override
	public String getStoragePath() {
		File file = new File(marbletaireActivity.getExternalCacheDir(), "marbletaireScreenshot" + new Date().getTime() + ".png");
		return file.getAbsolutePath();
	}
}
