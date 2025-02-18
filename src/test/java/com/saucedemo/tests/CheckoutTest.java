package com.saucedemo.tests;

import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutInformationPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CheckoutInformationPage checkoutInfoPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void setup() {
        // Set up the ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        
        // Navigate to the SauceDemo login page
        driver.get("https://www.saucedemo.com/");
        
        // Log in using valid credentials
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        
        // On the Products page, add a product to the cart
        productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("Sauce Labs Backpack");
        
        // Navigate to the shopping cart page
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @Test
    public void testCheckoutProcess() {
        // Click on the checkout button on the shopping cart page
        driver.findElement(By.id("checkout")).click();
        
        // Fill in the checkout information
        checkoutInfoPage = new CheckoutInformationPage(driver);
        checkoutInfoPage.enterFirstName("John");
        checkoutInfoPage.enterLastName("Doe");
        checkoutInfoPage.enterPostalCode("12345");
        checkoutInfoPage.clickContinue();
        
        // On the checkout overview page, verify the item total is displayed correctly (optional)
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        String itemTotal = checkoutOverviewPage.getItemTotal();
        Assert.assertTrue(itemTotal.contains("Item total:"), "Item total should be displayed on checkout overview.");
        checkoutOverviewPage.clickFinish();
        
        // On the checkout complete page, verify the order confirmation message
        checkoutCompletePage = new CheckoutCompletePage(driver);
        String completeHeader = checkoutCompletePage.getCompleteHeader();
        Assert.assertEquals(completeHeader, "Thank you for your order!", "Order completion header did not match expected text.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
