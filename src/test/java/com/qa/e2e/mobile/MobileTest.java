package com.qa.e2e.mobile;

import org.testng.annotations.Test;

import com.qa.Base.BaseClass;
import com.qa.lib.Wait;
import com.qa.lib.getMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MobileTest extends BaseClass{
    
    @Test
    public void getAPK_UP() {
        try {
            getMobileElement.byId(mDriver, "cf.projectspro.bank:id/email").sendKeys("Hello@gmail.com");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }


}
