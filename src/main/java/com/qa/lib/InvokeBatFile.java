package com.qa.lib;

import java.io.IOException;

public class InvokeBatFile {
    
    public InvokeBatFile(String filePath){
        String sFilePath = GetCurrDir.currDir()+"/"+filePath;
        try {
            Runtime.getRuntime().exec(sFilePath);
            Thread.sleep(5000);
            System.out.println("Bat file ran successfully!!!!!!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
