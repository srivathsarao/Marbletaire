package com.ashtav.marbletaire.client;

import com.ashtav.marbletaire.model.Runner;
import com.ashtav.marbletaire.model.RunnerFactory;

public class HtmlRunnerFactory implements RunnerFactory {

	@Override
	public Runner createRunner() {
		return new HtmlRunner();
	}
}
