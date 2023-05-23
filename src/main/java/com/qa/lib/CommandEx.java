package com.qa.lib;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class CommandEx {
    public static void navigateToDir(String directoryPath) {
        File directory = new File(directoryPath);
        System.out.println("Navigating to directory: " + directory.getAbsolutePath());
        System.setProperty("user.dir", directory.getAbsolutePath());
    }

    public static void EXECUTE(String command) {
        try {
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").startsWith("Windows")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            } else {
                String[] shellCommand;
                if (System.getProperty("os.name").contains("nix") || System.getProperty("os.name").contains("nux")) {
                    shellCommand = new String[]{"bash", "-c", command};
                } else if (System.getProperty("os.name").contains("Mac")) {
                    shellCommand = new String[]{"zsh", "-c", command};
                } else {
                    System.out.println("Unsupported operating system.");
                    return;
                }
                processBuilder = new ProcessBuilder(shellCommand);
            }
            // Set the working directory
            String directoryPath = System.getProperty("user.dir");
            processBuilder.directory(new File(directoryPath));
            System.out.println("Executing command: " + command);
            Process process = processBuilder.start();

            // Read the output of the command
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the command to finish
            int exitCode = process.waitFor();
            System.out.println("Command executed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isRunning(String url, int port) {
        try (Socket socket = new Socket(url, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static boolean pingUrl(String url) {
        try {
            InetAddress address = InetAddress.getByName(url);
            return address.isReachable(5000);
        } catch (IOException e) {
            return false;
        }
    }
}
