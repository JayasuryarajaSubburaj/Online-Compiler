package com.onlinecompiler.service;

import java.util.concurrent.Callable;

import com.onlinecompiler.util.PythonRunner;

public class PythonRunnerTask implements Callable<String> {
	
	private String code;

    public PythonRunnerTask(String code) {
        this.code = code;
    }

    @Override
    public String call() {
        return PythonRunner.runPythonCode(code);
    }

}
