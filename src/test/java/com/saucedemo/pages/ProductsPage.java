package com.saucedemo.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    WebDriver driver;

    private By productItems = By.className("inventory_item");
    private By sortDropdown = By.className("product_sort_container");
    private By cartBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Returns a list of product elements
    public List<WebElement> getAllProducts() {
        return driver.findElements(productItems);
    }

    // Clicks on 'Add to cart' button on a specific product
    public void addProductToCart(String productName) {
        List<WebElement> products = getAllProducts();
        for (WebElement product : products) {
            String name = product.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement addToCartButton = product.findElement(By.xpath(".//button[text()='Add to cart']"));
                addToCartButton.click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(cartBadge), "1"));
                break;
            }
        }
    }

    // Clicks on a specific product
    public void clickProduct(String productName) {
        List<WebElement> products = getAllProducts();
        for (WebElement product : products) {
            WebElement name = product.findElement(By.className("inventory_item_name"));
            String name_str = name.getText();
            if (name_str.equalsIgnoreCase(productName)) {
                name.click();
                break;
            }
        }
    }

    // Return the count of items in the shopping cart
    public int getCartItemCount() {
        try {
            WebElement badge = driver.findElement(cartBadge);
            return Integer.parseInt(badge.getText());
        } catch (Exception e) {
            // If badge is not present, cart is empty
            return 0;
        }
    }

    // Returns the name of the first product in the list
    public String getFirstProductName() {
        return driver.findElements(By.className("inventory_item_name")).get(0).getText();
    }

    // Selects a sorting option from the dropdown
    public void sortProducts(String sortOption) {
        WebElement dropdown = driver.findElement(sortDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(sortOption);
    }
}
