package com.qa.lib;

import java.io.IOException;

public class InvokeBatFile {
    public InvokeBatFile(String filePath){
        int xCode = executeBatchFile(filePath);
        if (xCode <= 0) ThrowError.error();
    }
    private int executeBatchFile(String filePath) {
        ProcessBuilder processBuilder = null;
        Process process = null;
        int exitCode = 0;
        if (!OSUTIL.getOS().equalsIgnoreCase("windows")) {
            ThrowError.error();
        }
        try {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", GetCurrDir.currDir() + "/" + filePath);
            process = processBuilder.start();
            exitCode = process.waitFor();
            System.out.println("Batch file executed with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            } else {
                System.out.println("Already closed!!!");
            }
        }
        return exitCode;
    }

}
