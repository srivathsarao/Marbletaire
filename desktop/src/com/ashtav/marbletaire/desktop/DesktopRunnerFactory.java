package com.ashtav.marbletaire.desktop;

import com.ashtav.marbletaire.model.Runner;
import com.ashtav.marbletaire.model.RunnerFactory;

public class DesktopRunnerFactory implements RunnerFactory {

	@Override
	public Runner createRunner() {
		return new DesktopRunner();
	}

}
