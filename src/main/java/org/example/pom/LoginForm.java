package org.example.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class LoginForm {
    private Page page;
    private Locator userNameInput;
    private Locator passwordInput;
    private Locator logInButton;
    public LoginForm(Page page){
        this.page=page;
        this.userNameInput=page.locator("//div[@class='modal-content']//input[@id='username-modal']");
        this.passwordInput=page.locator("//div[@class='modal-content']//input[@id='password-modal']");
        this.logInButton=page.locator("//div[@class='modal-content']//button[contains(@onclick,'login')]");
    }

    @Step("Fill username")
    public void fillUserName(String userName){
        userNameInput.fill(userName);
    }

    @Step("Fill password")
    public void fillPassword(String password){
        passwordInput.fill(password);
    }
    @Step("Click logIn button")
    public void clickLogInButton(){
        logInButton.click();
    }
@Step("Fill all login fields and click 'LogIn' button")
    public void fillAllFieldsAndClickLogInButton(String userName,String password){
        fillUserName(userName);
        fillPassword(password);
        clickLogInButton();
}

}
