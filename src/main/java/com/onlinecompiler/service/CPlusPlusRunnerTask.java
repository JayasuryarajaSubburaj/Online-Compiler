package com.onlinecompiler.service;

import java.util.concurrent.Callable;

import com.onlinecompiler.util.CPlusPlusRunner;

public class CPlusPlusRunnerTask implements Callable<String> {


    private String code;

    public CPlusPlusRunnerTask(String code) {
        this.code = code;
    }

    @Override
    public String call() {
        return CPlusPlusRunner.runCPlusPlusCode(code);
    }
}
