package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.pages.ShoppingCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ShoppingCartTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    ShoppingCartPage shoppingCartPage;

    @BeforeMethod
    public void setup() {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login using the LoginPage
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Add a product using the ProductsPage
        productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("Sauce Labs Backpack");

        // Navigate to the shopping cart page by clicking the cart icon
        driver.findElement(By.className("shopping_cart_link")).click();

        // Initialize the ShoppingCartPage
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @Test
    public void testCartItemCount() {
        // Verify that one product is present in the cart
        int count = shoppingCartPage.getCartItemCount();
        Assert.assertEquals(count, 1, "Cart item count should be 1 after adding a product");
    }

    @Test
    public void testRemoveItemFromCart() {
        // Remove the product and verify the cart is empty
        shoppingCartPage.removeItem("Sauce Labs Backpack");
        int countAfterRemoval = shoppingCartPage.getCartItemCount();
        Assert.assertEquals(countAfterRemoval, 0, "Cart should be empty after removing the product");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
