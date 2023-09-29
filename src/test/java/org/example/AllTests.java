package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Description;
import org.example.configuration.BrowserSettings;
import org.example.data.DataBuilder;
import org.example.pom.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Class with all tests")
class AllTests {
    private Page page;
    private DataBuilder data = new DataBuilder();
    private MainPage mainPage;
    private RegistrationForm registrationForm;
    private CataloguePage cataloguePage;
    private CartPage cartPage;
    private LoginForm loginForm;

    @BeforeEach
    public void setUp() {
        page = BrowserSettings.getNavigate();
        mainPage = new MainPage(page);
        registrationForm = new RegistrationForm(page);
        cataloguePage = new CataloguePage(page);
        cartPage = new CartPage(page);
        loginForm = new LoginForm(page);
        page.navigate(data.getURL());
    }

    @Test
    @DisplayName("Checking the total amount of items in the basket")
    @Description("e2e test for authorization and adding goods to the cart with verification that the sum of the price of goods in the basket corresponds to the sum of the prices of the ordered goods")
    public void checkIfTotalCartPriceIsCorrectTest() throws InterruptedException {
        mainPage.clickRegisterButton();
        registrationForm.fillFullRegistrationFormAndClickRegister(
                data.getUserName(),
                data.getFirstMame(),
                data.getLastMame(),
                data.getEmail(),
                data.getPassword()
        );
        mainPage.clickLogOutButton();
        mainPage.clickLogInButton();
        loginForm.fillAllFieldsAndClickLogInButton(data.getUserName(), data.getPassword());
        mainPage.clickCatalogue();
        for (int i = 0; i < 3; i++) {
            cataloguePage.addProductToCart(data.getAllProducts().get(i));
        }
        mainPage.moveToCart();
        Thread.sleep(1000);
        double expected = cartPage.countAllItemsPricesInTotal();
        double actual = cartPage.getTotalCartPrice();
        assertEquals(expected, actual, "Checking if total cart price equals to sum of every total price of each item in the cat");

    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

}