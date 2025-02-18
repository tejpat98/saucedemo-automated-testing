package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductsTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Navigate to SauceDemo login page
        driver.get("https://www.saucedemo.com/");
        // Initialize and perform login (using the LoginPage POM)
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        // Now on the products page, initialize ProductsPage
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void testProductsListNotEmpty() {
        // Verify that the products page displays at least one product
        int productCount = productsPage.getAllProducts().size();
        Assert.assertTrue(productCount > 0, "Product list should not be empty");
    }

    @Test
    public void testAddProductToCart() {
        // For example, add "Sauce Labs Backpack" to the cart
        productsPage.addProductToCart("Sauce Labs Backpack");
        // Verify the shopping cart shows one item after addition
        int cartCount = productsPage.getCartItemCount();
        Assert.assertEquals(cartCount, 1, "Cart item count should be 1 after adding the product");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
