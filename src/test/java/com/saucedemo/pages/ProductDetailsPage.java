package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    WebDriver driver;
    
    // Locators for the product details page
    private By productName = By.className("inventory_details_name");
    private By productDescription = By.className("inventory_details_desc");
    private By productPrice = By.className("inventory_details_price");
    private By backButton = By.id("back-to-products");
    
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getProductName() {
        return driver.findElement(productName).getText();
    }
    
    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }
    
    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }
    
    public void clickBackToProducts() {
        driver.findElement(backButton).click();
    }
}
