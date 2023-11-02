package com.onlinecompiler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineCompilerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCompilerApplication.class, args);
	}
	
	@Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(8); // 5 threads, you can adjust this number
    }

}
