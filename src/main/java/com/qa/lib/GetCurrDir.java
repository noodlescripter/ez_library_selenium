package com.qa.lib;

public class GetCurrDir {
    
    public static String currDir() {
        return System.getProperty("user.dir")+"/";
    }

}
