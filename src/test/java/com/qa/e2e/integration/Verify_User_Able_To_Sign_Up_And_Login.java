package com.qa.e2e.integration;

import com.qa.Base.BaseClass;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class Verify_User_Able_To_Sign_Up_And_Login extends BaseClass {

   private String[] userInformation = {
      "Hamim", "Alam", "12345678", "hamim.alam@qa.com", "01021001", "12345678", "hamimalam"
    };

    @Test
    public void a_verify_that_sign_up_window_is_working_as_expected() {
        Assert.assertEquals(lib.main.GET_TEXT_BY_TAG_NAME("h1"), "Login To Application");
        Assert.assertEquals(lib.main.IS_ELEMENT_PRESENT_XPATH("//button[@type='submit']"), true);
        String mainWindow = driver.getWindowHandle();
        Assert.assertEquals(lib.main.CLICK_BY_TEXT("Sign Up"), "Pass");
        lib.window.switchTo();
        Assert.assertEquals(lib.main.GET_TEXT_BY_TAG_NAME("h1"), "Sign Up Page");
        lib.window.closeDialog();
        lib.window.switchBack(mainWindow);
        Assert.assertEquals(lib.main.GET_TEXT_BY_TAG_NAME("h1"), "Login To Application");
        lib.wait.hardWait(3000);
    }

    @Test
    public void b_verify_that_user_able_to_sign_up() throws SQLException, ClassNotFoundException {
        Assert.assertEquals(lib.main.GET_TEXT_BY_TAG_NAME("h1"), "Login To Application");
        Assert.assertEquals(lib.main.IS_ELEMENT_PRESENT_XPATH("//button[@id='signupButton']"), true);
        String mainwidow = driver.getWindowHandle();
        Assert.assertEquals(lib.main.CLICK_BY_TEXT("Sign Up"), "Pass");
        lib.window.switchTo();
        Assert.assertEquals(lib.main.GET_TEXT_BY_TAG_NAME("h1"), "Sign Up Page");
        Assert.assertEquals(lib.main.TYPE_BY_CSS("[id='firstName']", userInformation[0]), "Pass");
        Assert.assertEquals(lib.main.TYPE_BY_CSS("[id='lastName']", userInformation[1]), "Pass");
        Assert.assertEquals(lib.main.TYPE_BY_CSS("[id='phoneNumber']", userInformation[2]), "Pass");
        Assert.assertEquals(lib.main.TYPE_BY_CSS("[id='email']", userInformation[3]), "Pass");
        Assert.assertEquals(lib.main.TYPE_BY_CSS("[id='dob']", userInformation[4]), "Pass");
        Assert.assertEquals(lib.main.TYPE_BY_CSS("[id='password']", userInformation[5]), "Pass");
        Assert.assertEquals(lib.main.TYPE_BY_CSS("[id='username']", userInformation[6]), "Pass");
        Assert.assertEquals(lib.main.CLICK_BY_XPATH("//button[@type='submit']"), "Pass");
        lib.wait.hardWait(1000);
        lib.window.acceptAlert(true);
        lib.window.closeDialog();
        lib.window.switchBack(mainwidow);
        String firstName = lib.db.query("SELECT username FROM user_table where first_name = 'Hamim'");
        Assert.assertEquals(firstName, userInformation[6]);
    }

    @Test
    public void c_make_an_api_call_and_verify_that_user_has_been_created() {
        String username = "hamimalam";
        String first_name = "Hamim";
        String last_name = "Alam";
        Response response = lib.ui_api.get("/users");
        Assert.assertTrue(response.jsonPath().getString("username").contains(username));
        Assert.assertTrue(response.jsonPath().getString("first_name").contains(first_name));
        Assert.assertTrue(response.jsonPath().getString("last_name").contains(last_name));
    }

    @Test
    public void d_login_to_application_using_newly_created_user(){
        Assert.assertEquals(lib.main.GET_TEXT_BY_TAG_NAME("h1"), "Login To Application");
        Assert.assertEquals(lib.main.TYPE_BY_ID("username", "hamimalam"), "Pass");
        Assert.assertEquals(lib.main.TYPE_BY_ID("password", "12345678"), "Pass");
        Assert.assertEquals(lib.main.CLICK_BY_TEXT("Log In"), "Pass");
        Assert.assertTrue(lib.main.IS_ELEMENT_PRESENT_XPATH("//*[@class='card-title']"));
        Assert.assertTrue(lib.main.GET_TEXT_BY_ID("userEmail").contains("hamim.alam@qa.com"));
        Assert.assertEquals(lib.main.CLICK_BY_TEXT("Logout"), "Pass");
    }

    @Test
    public void e_login_to_application_using_newly_created_user_and_delete_and_verify(){
        Assert.assertEquals(lib.main.GET_TEXT_BY_TAG_NAME("h1"), "Login To Application");
        Assert.assertEquals(lib.main.TYPE_BY_ID("username", "hamimalam"), "Pass");
        Assert.assertEquals(lib.main.TYPE_BY_ID("password", "12345678"), "Pass");
        Assert.assertEquals(lib.main.CLICK_BY_TEXT("Log In"), "Pass");
        Assert.assertTrue(lib.main.IS_ELEMENT_PRESENT_XPATH("//*[@class='card-title']"));
        Assert.assertEquals(lib.main.CLICK_BY_TEXT("Edit"), "Pass");
        Assert.assertEquals(lib.main.CLICK_BY_TEXT("Delete User"), "Pass");
        lib.window.acceptAlert(true);
    }

    @Test
    public void f_make_an_api_call_and_verify_that_user_has_been_deleted() {
        String username = "hamimalam";
        String first_name = "Hamim";
        String last_name = "Alam";
        Response response = lib.ui_api.get("/users");
        Assert.assertFalse(response.jsonPath().getString("username").contains(username));
        Assert.assertFalse(response.jsonPath().getString("first_name").contains(first_name));
        Assert.assertFalse(response.jsonPath().getString("last_name").contains(last_name));
    }
}
