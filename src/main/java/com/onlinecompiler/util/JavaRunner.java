package com.onlinecompiler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaRunner {

	public static String runJavaCode(String code) {
		String fileName = "/home/surya/Main.java";
		StringBuilder output = new StringBuilder();

		try {
			// Write the code to a file
			Files.write(Paths.get(fileName), code.getBytes());

			// Step 1: Compile the Java program
			ProcessBuilder compileProcessBuilder = new ProcessBuilder("javac", fileName);
			Process compileProcess = compileProcessBuilder.start();
			compileProcess.waitFor();

			// Check if compilation was successful
			if (compileProcess.exitValue() != 0) {
				output.append("Compilation error!\n");
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
				String errorLine;
				while ((errorLine = errorReader.readLine()) != null) {
					output.append("ERROR: ").append(errorLine).append("\n");
				}
				return output.toString().trim();
			}

			// Step 2: Run the compiled Java program
			ProcessBuilder runProcessBuilder = new ProcessBuilder("java", "-cp", "/home/surya", "Main");
			Process runProcess = runProcessBuilder.start();

			// Read the output
			BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}

			runProcess.waitFor();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}

		return output.toString().trim(); // Trim to remove trailing newline
	}
}
