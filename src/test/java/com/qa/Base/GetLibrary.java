package com.qa.Base;

import com.qa.api.lib.*;
import com.qa.db.DB;
import com.qa.lib.LIB;
import com.qa.lib.Wait;
import com.qa.lib.Window;
import org.openqa.selenium.WebDriver;

public class GetLibrary {

    public WebDriver driver = null;
    public LIB main = null;
    public Window window = null;
    public Wait wait = null;
    public DB db = null;
    public api_ui ui_api = null;

    //All the library needs to be imported here
    public GetLibrary(WebDriver driver) {
        this.driver = driver;
        main = new LIB(driver);
        window = new Window(driver);
        wait = new Wait(driver);
        db = new DB();
        ui_api = new api_ui("http://localhost:8001");
    }

}
