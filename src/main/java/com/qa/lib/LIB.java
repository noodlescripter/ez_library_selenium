package com.qa.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


/*This is main lib where all the library functions present*/
public class LIB {
    
    private String sConsole;
    private WebDriver driver;
    private ArrayList<String> listOfText = null;

    public LIB(WebDriver driver) {
        this.driver = driver;
    }

    public String CLICK_BY_ID(String ID){
        By sEle = By.id(ID);
        boolean bRes = Wrapper.IS_ELEMENT_CLICKABLE(driver, sEle);
        if(bRes){
            driver.findElement(sEle).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_BY_CSS(String ID){
        By sEle = By.cssSelector(ID);
        boolean bRes = Wrapper.IS_ELEMENT_CLICKABLE(driver, sEle);
        if(bRes){
            driver.findElement(sEle).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_BY_NAME(String ID){
        By sEle = By.name(ID);
        boolean bRes = Wrapper.IS_ELEMENT_CLICKABLE(driver, sEle);
        if(bRes){
            driver.findElement(sEle).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_BY_XPATH(String ID){
        By sEle = By.xpath(ID);
        boolean bRes = Wrapper.IS_ELEMENT_CLICKABLE(driver, sEle);
        if(bRes){
            driver.findElement(sEle).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String TYPE_BY_ID(String ID, String sVal){
        By sEle = By.id(ID);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sEle);
        if(bRes){
            driver.findElement(sEle).clear();
            driver.findElement(sEle).sendKeys(sVal);
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }
    public String TYPE_BY_NAME(String ID, String sVal){
        By sEle = By.name(ID);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sEle);
        if(bRes){
            driver.findElement(sEle).clear();
            driver.findElement(sEle).sendKeys(sVal);
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }
    public String TYPE_BY_CSS(String ID, String sVal){
        By sEle = By.cssSelector(ID);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sEle);
        if(bRes){
            driver.findElement(sEle).clear();
            driver.findElement(sEle).sendKeys(sVal);
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String TYPE_BY_XPATH(String ID, String sVal){
        By sEle = By.xpath(ID);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sEle);
        if(bRes){
            driver.findElement(sEle).clear();
            driver.findElement(sEle).sendKeys(sVal);
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String GET_TEXT_BY_XPATH(String ele){
        By sEle = By.xpath(ele);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sEle);
        if(bRes){
            sConsole = driver.findElement(sEle).getText();
        } else {
            sConsole = "Fail: Not able to find the element :(";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String GET_TEXT_BY_CSS(String ele){
        By sEle = By.cssSelector(ele);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sEle);
        if(bRes){
            sConsole = driver.findElement(sEle).getText();
        } else {
            sConsole = "Fail: Not able to find the element :(";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String GET_TEXT_BY_ID(String ele){
        By sEle = By.id(ele);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sEle);
        if(bRes){
            sConsole = driver.findElement(sEle).getText();
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String GET_TEXT_BY_NAME(String ele){
        By sEle = By.name(ele);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sEle);
        if(bRes){
            sConsole = driver.findElement(sEle).getText();
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_BY_TEXT(String ele) {
        By sEle = By.xpath("//*[normalize-space(text())='" + ele + "']");
        boolean bRes = Wrapper.IS_ELEMENT_CLICKABLE(driver, sEle);
        if (bRes) {
            driver.findElement(sEle).click();
            sConsole = "Pass: Clicked on :" + ele;
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public ArrayList<String> GET_LIST_OF_TEXT(String eles) {
        By sEles = By.xpath(eles);
        //getting list of elements first
        List<WebElement> allEles = driver.findElements(sEles);
        if (allEles.size() < 1) {
            ThrowError.error();
        }
        for (WebElement ele : allEles) {
            if(Wrapper.WAIT_FOR_ELEMENT_ELE(driver, ele)){
                listOfText = new ArrayList<>();
                String text = ele.getText();
                System.out.println(text);
                listOfText.add(text.trim());
            }
        }
        return listOfText;
    }

    public String COMPARE_LIST_OF_TEXT(ArrayList<String> actualText, ArrayList<String> expectedText) {
        //Validation checkpoint
        if (actualText.size() != expectedText.size()) {
            ThrowError.error();
        }
        //assuming both arr list has same size!!
        for (int i = 0; i < actualText.size(); i++) {
            if (actualText.get(i) != expectedText.get(i)) {
                sConsole = "Fails";
            } else {
                sConsole = "Pass";
            }
            System.out.println(sConsole);
            return sConsole;
        }
        return sConsole;
    }

    public String CLICK_LAST_ELEMENT_BY_XPATH(String element) {
        List<WebElement> allEles = driver.findElements(By.xpath(element));
        if (allEles.size() > 0) {
            System.out.println("More than One element found last one will be used");
            Wrapper.WAIT_FOR_ELEMENT_ELE(driver, allEles.get(allEles.size() - 1));
            allEles.get(allEles.size() - 1).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_FIRST_ELEMENT_BY_XPATH(String element) {
        List<WebElement> allEles = driver.findElements(By.xpath(element));
        if (allEles.size() > 0) {
            System.out.println("More than One element found first one will be used");
            Wrapper.WAIT_FOR_ELEMENT_ELE(driver, allEles.get(0));
            allEles.get(0).click();
            sConsole = "Pass";
        } else {
            sConsole = "Failed: Not able to click";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_LAST_ELEMENT_BY_CSS(String element) {
        List<WebElement> allEles = driver.findElements(By.cssSelector(element));
        if (allEles.size() > 0) {
            System.out.println("More than One element found last one will be used");
            Wrapper.WAIT_FOR_ELEMENT_ELE(driver, allEles.get(allEles.size() - 1));
            allEles.get(allEles.size() - 1).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_FIRST_ELEMENT_BY_CSS(String element) {
        List<WebElement> allEles = driver.findElements(By.cssSelector(element));
        if (allEles.size() > 0) {
            System.out.println("More than One element found first one will be used");
            Wrapper.WAIT_FOR_ELEMENT_ELE(driver, allEles.get(0));
            allEles.get(0).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_LAST_ELEMENT_BY_TEXT(String element) {
        List<WebElement> allEles = driver.findElements(By.xpath("//*[normalize-space(text())='"+element+"']"));
        if (allEles.size() > 0) {
            System.out.println("More than One element found last one will be used");
            Wrapper.WAIT_FOR_ELEMENT_ELE(driver, allEles.get(allEles.size() - 1));
            allEles.get(allEles.size() - 1).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public String CLICK_FIRST_ELEMENT_BY_TEXT(String element) {
        List<WebElement> allEles = driver.findElements(By.xpath("//*[normalize-space(text())='"+element+"']"));
        if (allEles.size() > 0) {
            System.out.println("More than One element found first one will be used");
            Wrapper.WAIT_FOR_ELEMENT_ELE(driver, allEles.get(0));
            allEles.get(0).click();
            sConsole = "Pass";
        } else {
            sConsole = "Fail";
        }
        System.out.println(sConsole);
        return sConsole;
    }

    public boolean IS_SELECTED_BY_XPATH(String eles) {
        boolean sConsole = false;
        By sBy = By.xpath(eles);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sBy);
        if (driver.findElement(sBy).isSelected())
            sConsole = true;
        System.out.println("Element is " + sConsole);
        return sConsole;
    }

    public boolean IS_SELECTED_BY_CSS(String eles) {
        boolean sConsole = false;
        By sBy = By.cssSelector(eles);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sBy);
        if (driver.findElement(sBy).isSelected())
            sConsole = true;
        System.out.println("Element is " + sConsole);
        return sConsole;
    }

    public boolean IS_SELECTED_BY_NAME(String eles) {
        boolean sConsole = false;
        By sBy = By.name(eles);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sBy);
        if (driver.findElement(sBy).isSelected())
            sConsole = true;
        System.out.println("Element is " + sConsole);
        return sConsole;
    }

    public boolean IS_SELECTED_BY_ID(String eles) {
        boolean sConsole = false;
        By sBy = By.id(eles);
        boolean bRes = Wrapper.WAIT_UNTIL_ELEMENT_IS_PRESENT(driver, sBy);
        if (driver.findElement(sBy).isSelected())
            sConsole = true;
        System.out.println("Element is " + sConsole);
        return sConsole;
    }
}
