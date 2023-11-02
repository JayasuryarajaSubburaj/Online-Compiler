package com.onlinecompiler.service;

import java.util.concurrent.Callable;

import com.onlinecompiler.util.CRunner;

public class CRunnerTask implements Callable<String> {

	private String code;

	public CRunnerTask(String code) {
		this.code = code;
	}

	@Override
	public String call() {
		return CRunner.runCCode(code);
	}
}
