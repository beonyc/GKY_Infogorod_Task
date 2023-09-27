package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Description;
import org.example.data.DataBuilder;
import org.example.pom.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Class with all tests")
class AllTests {
    private Browser browser;
    private Page page;
    public final String URL = "http://localhost/index.html";
    private DataBuilder data = new DataBuilder();
    private MainPage mainPage;
    private RegistrationForm registrationForm;
    private CataloguePage cataloguePage;
    private CartPage cartPage;
    private LoginForm loginForm;

    @BeforeEach
    public void setUp() {
        browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        mainPage = new MainPage(page);
        registrationForm = new RegistrationForm(page);
        cataloguePage = new CataloguePage(page);
        cartPage = new CartPage(page);
        loginForm = new LoginForm(page);
        page.navigate(URL);
    }

    @Test
    @DisplayName("Checking the total amount of items in the basket")
    @Description("e2e test for authorization and adding goods to the cart with verification that the sum of the price of goods in the basket corresponds to the sum of the prices of the ordered goods")
    public void checkIfTotalCartPriceIsCorrectTest() {
        try {
            //Нажимаем кнопку зарегистрироваться на главной странице
            mainPage.clickRegisterButton();
            //заполняем все поля и нажимаем кнопку зарегистрироваться
            registrationForm.fillFullRegistrationFormAndClickRegister(
                    data.getUserName(),
                    data.getFirstMame(),
                    data.getLastMame(),
                    data.getEmail(),
                    data.getPassword()
            );
            //Нажимаем выйти с аккаунта
            mainPage.clickLogOutButton();
            //Нажимаем кнопку залогиниться
            mainPage.clickLogInButton();
            //Вводим все данные в окне авторизации и нажимаем войти
            loginForm.fillAllFieldsAndClickLogInButton(data.getUserName(), data.getPassword());
            //Переходим в каталог
            mainPage.clickCatalogue();
            //Добавление трёх продуктов в корзину
            for (int i = 0; i < 3; i++) {
                cataloguePage.addProductToCart(data.getAllProducts().get(i));
            }
            //Переходим в корзину
            mainPage.moveToCart();
            //Ждём пока перейдём в корзину
            Thread.sleep(1000);
            //Подсчёт всей корзины вручную
            double expected = cartPage.countAllItemsPricesInTotal();
            //Получение суммы товаров, которая сама посчиталась
            double actual = cartPage.getTotalCartPrice();
            assertEquals(expected, actual);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            page.close();
        }
    }

}