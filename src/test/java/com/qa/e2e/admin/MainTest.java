package com.qa.e2e.admin;

import com.qa.lib.Wrapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.Base.BaseClass;
import com.qa.lib.AppLogin;
import com.qa.lib.AppNavigate;

public class MainTest extends BaseClass {

    @Test
    public void validate_homepage_and_validate_listing_page() throws InterruptedException {
        String[] arrgs = {
                "Home",
                "Listings"
        };
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(lib.CLICK_BY_CSS("[aria-controls='offcanvasLeft']"), "Pass");
            Assert.assertEquals(lib.CLICK_LAST_ELEMENT_BY_TEXT(arrgs[i]), "Pass");
            if (arrgs[i].equals("Listings")) {
                Assert.assertEquals(lib.GET_TEXT_BY_CSS("button[class='filter_btn']"), "Advance filter");
            }
        }

    }

}
