package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailsPage;
import com.saucedemo.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductDetailsAndSortingTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        
        // Log in using LoginPage
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        
        // Initialize ProductsPage
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void testProductDetails() {
        // Click on a specific product to view details
        productsPage.clickProduct("Sauce Labs Backpack");
        
        // Initialize ProductDetailsPage and verify product details
        ProductDetailsPage productDetails = new ProductDetailsPage(driver);
        String name = productDetails.getProductName();
        Assert.assertEquals(name, "Sauce Labs Backpack", "Product name does not match");
        
        // Optionally, check description and price
        // String description = productDetails.getProductDescription();
        // String price = productDetails.getProductPrice();
        
        // Go back to the Products page
        productDetails.clickBackToProducts();
    }

    @Test
    public void testProductSorting() {
        // Capture the name of the first product before sorting
        String initialFirstProduct = productsPage.getFirstProductName();
        
        // Change sort order to "Price (low to high)"
        productsPage.sortProducts("Price (low to high)");
        
        // Wait for sorting to take effect (a simple Thread.sleep here; in a real project, use explicit waits)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Capture the first product name after sorting
        String sortedFirstProduct = productsPage.getFirstProductName();
        Assert.assertNotEquals(initialFirstProduct, sortedFirstProduct, "Sorting did not change the order of products");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
