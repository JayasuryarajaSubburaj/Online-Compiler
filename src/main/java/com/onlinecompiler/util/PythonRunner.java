package com.onlinecompiler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PythonRunner {

	public static String runPythonCode(String code) {
		String fileName = "/home/surya/main.py";
		StringBuilder output = new StringBuilder();

		try {
			// Write the code to a file
			Files.write(Paths.get(fileName), code.getBytes());

			ProcessBuilder processBuilder = new ProcessBuilder("python3", fileName);
			Process process = processBuilder.start();

			// Read the output
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}

			// Wait for the process to exit
			process.waitFor();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}

		return output.toString().trim();
	}

}
