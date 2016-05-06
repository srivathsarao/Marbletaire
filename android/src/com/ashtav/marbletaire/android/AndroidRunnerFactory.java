package com.ashtav.marbletaire.android;

import com.ashtav.marbletaire.model.Runner;
import com.ashtav.marbletaire.model.RunnerFactory;

public class AndroidRunnerFactory implements RunnerFactory {

	@Override
	public Runner createRunner() {
		return new AndroidRunner();
	}

}
