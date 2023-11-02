package com.onlinecompiler.service;

import java.util.concurrent.Callable;

import com.onlinecompiler.util.JavaRunner;

public class JavaRunnerTask implements Callable<String>{

	private String code;

    public JavaRunnerTask(String code) {
        this.code = code;
    }

    @Override
    public String call() {
        return JavaRunner.runJavaCode(code);
    }
}
