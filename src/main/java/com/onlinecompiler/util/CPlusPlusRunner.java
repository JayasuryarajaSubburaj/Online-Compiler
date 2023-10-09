package com.onlinecompiler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CPlusPlusRunner {

    public static String runCPlusPlusCode(String code) {
        String fileName = "/home/surya/main.cpp";
        StringBuilder output = new StringBuilder();

        try {
            // Write the code to a file
            Files.write(Paths.get(fileName), code.getBytes());

            // Step 1: Compile the C++ program
            ProcessBuilder compileProcessBuilder = new ProcessBuilder("g++", "-o", "/home/surya/output", fileName);
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

            // Step 2: Run the compiled C++ program
            ProcessBuilder runProcessBuilder = new ProcessBuilder("/home/surya/output");
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

