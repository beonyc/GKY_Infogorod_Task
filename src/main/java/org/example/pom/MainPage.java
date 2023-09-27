package org.example.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class MainPage {
    private Page page;
    private Locator registrationButton;
    private Locator loginButton;
    private Locator logOutButton;
    private Locator catalogue;
    private Locator cart;

    public MainPage(Page page) {
        this.page = page;
        this.registrationButton = page.locator("//*[@id='register']");
        this.loginButton = page.locator("//*[@id='login']");
        this.catalogue = page.locator(".dropdown-toggle");
        this.cart = page.locator(".navbar-buttons a[href='basket.html']");
        this.logOutButton = page.locator("//ul//a[text()='Logout']");
    }

    @Step("Click logout button")
    public void clickLogOutButton() {
        logOutButton.click();
    }
    @Step("Click logIn Button")
    public void clickLogInButton(){
        loginButton.click();
    }

    @Step("Click \"Register\" button")
    public void clickRegisterButton() {
        registrationButton.click();
    }

    @Step("Click to CATALOGUE")
    public void clickCatalogue() {
        catalogue.click();
    }

    @Step("Move to cart")
    public void moveToCart() {
        cart.click();
    }
}
