package org.example.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class RegistrationForm {
   private Page page;
   private Locator userNameInput;
   private Locator firstMameInput;
   private Locator lastMameInput;
   private Locator emailInput;
   private Locator passwordInput;
   private Locator submitRegistrationButton;

    public RegistrationForm(Page page) {
        this.page = page;
        this.userNameInput=page.locator("#register-username-modal");
        this.firstMameInput=page.locator("#register-first-modal");
        this.lastMameInput=page.locator("#register-last-modal");
        this.emailInput=page.locator("#register-email-modal");
        this.passwordInput=page.locator("#register-password-modal");
        this.submitRegistrationButton=page.locator("//button[@class='btn btn-primary' and contains(@onclick,'register')]");
    }

    @Step("Fill userName")
    public void fillUserName(String name){
        userNameInput.fill(name);
    }
    @Step("Fill First name")
    public void fillFirstName(String firstName){
        firstMameInput.fill(firstName);
    }
    @Step("Fill Last name")
    public void fillLastName(String lastName){
        lastMameInput.fill(lastName);
    }
    @Step("Fill email")
    public void fillEmail(String email){
        emailInput.fill(email);
    }
    @Step("Fill password")
    public void fillPassword(String password){
        passwordInput.fill(password);
    }
    @Step("Click to registration button")
    public void clickSubmitRegistrationButton(){
        submitRegistrationButton.click();
    }

    @Step("Fill all fields and click the Register button")
    public void fillFullRegistrationFormAndClickRegister(String userName,String firstName,String lastName,String email,String password){
        fillUserName(userName);
        fillFirstName(firstName);
        fillLastName(lastName);
        fillEmail(email);
        fillPassword(password);
        clickSubmitRegistrationButton();
    }
}
