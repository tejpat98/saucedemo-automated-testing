package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    WebDriver driver;

    private By completeHeader = By.className("complete-header");
    private By completeText = By.className("complete-text");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getCompleteHeader() {
        return driver.findElement(completeHeader).getText();
    }
    
    public String getCompleteText() {
        return driver.findElement(completeText).getText();
    }
}
