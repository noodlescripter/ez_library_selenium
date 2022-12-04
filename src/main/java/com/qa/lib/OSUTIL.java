package com.qa.lib;

import java.io.IOException;
import java.net.InetAddress;

public class OSUTIL {
    
    public static String getOS() {
        return System.getProperty("os.name");
    }

    public static String getLocalHost() {
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostAddress();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return hostName;
    }


}
