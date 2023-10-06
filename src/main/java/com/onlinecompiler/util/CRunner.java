//package com.onlinecompiler.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class CRunner {
//	
//	public static void runCCode(String code) {
//        // Step 1: Compile the C program
//        ProcessBuilder compileProcessBuilder = new ProcessBuilder("gcc", "-o", "/home/surya/output", "/home/surya/jsr.c");
//        try {
//            Process compileProcess = compileProcessBuilder.start();
//            compileProcess.waitFor();
//
//            // Check if compilation was successful
//            if (compileProcess.exitValue() != 0) {
//                System.out.println("Compilation error!");
//                BufferedReader errorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
//                String errorLine;
//                while ((errorLine = errorReader.readLine()) != null) {
//                    System.out.println("ERROR: " + errorLine);          
//                }
//                return;
//            }
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        // Step 2: Run the compiled C program
//        ProcessBuilder runProcessBuilder = new ProcessBuilder("/home/surya/output");
//        try {
//            Process runProcess = runProcessBuilder.start();
//
//            // Read the output
//            BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // Wait for the process to exit
//            runProcess.waitFor();
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//}

package com.onlinecompiler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CRunner {

    public static void runCCode(String code) {
        String fileName = "/home/surya/main.c";
        
        try {
            // Write the code to a file
            Files.write(Paths.get(fileName), code.getBytes());

            // Step 1: Compile the C program
            ProcessBuilder compileProcessBuilder = new ProcessBuilder("gcc", "-o", "/home/surya/output", fileName);
            Process compileProcess = compileProcessBuilder.start();
            compileProcess.waitFor();

            // Check if compilation was successful
            if (compileProcess.exitValue() != 0) {
                System.out.println("Compilation error!");
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    System.out.println("ERROR: " + errorLine);
                }
                return;
            }

            // Step 2: Run the compiled C program
            ProcessBuilder runProcessBuilder = new ProcessBuilder("/home/surya/output");
            Process runProcess = runProcessBuilder.start();

            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to exit
            runProcess.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

