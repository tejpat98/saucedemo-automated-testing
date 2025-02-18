package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ShoppingCartPage {
    WebDriver driver;

    // Locator for cart items
    private By cartItems = By.className("cart_item");
    
    // Locator for checkout button
    private By checkoutButton = By.id("checkout");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Get a list of cart items
    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }
    
    // Return the count of items in the cart
    public int getCartItemCount() {
        return getCartItems().size();
    }
    
    // Remove an item from the cart by its product name
    public void removeItem(String productName) {
        List<WebElement> items = getCartItems();
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                // Click the "Remove" button within this cart item
                item.findElement(By.xpath(".//button[contains(text(), 'Remove')]")).click();
                break;
            }
        }
    }
    
    // Click the checkout button
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
