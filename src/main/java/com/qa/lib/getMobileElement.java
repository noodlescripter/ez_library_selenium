package com.qa.lib;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class getMobileElement {
    
    public static MobileElement byId(AndroidDriver mDriver, String id) {
        return Wait.waitForMobileEle(mDriver, id);
    }
}
