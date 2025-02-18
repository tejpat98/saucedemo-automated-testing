package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {
    WebDriver driver;

    private By finishButton = By.id("finish");
    private By itemTotal = By.className("summary_subtotal_label");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getItemTotal() {
        return driver.findElement(itemTotal).getText();
    }
    
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }
}
