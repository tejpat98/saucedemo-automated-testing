package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LogoutPage {
    WebDriver driver;

    // Locators for the burger menu button and logout link
    private By burgerMenuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Opens the side menu
    public void openMenu() {
        driver.findElement(burgerMenuButton).click();
    }

    // Clicks the logout link in the side menu
    public void clickLogout() {
        // Since the logout button in inside the burgerMenu, wait for logoutLink to be clickable (visible) first
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickableLogout = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        driver.findElement(logoutLink).click();
    }

    // Combined method to perform logout
    public void logout() {
        openMenu();
        clickLogout();
    }
}
