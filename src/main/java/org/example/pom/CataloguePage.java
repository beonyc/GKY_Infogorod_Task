package org.example.pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class CataloguePage {
    private Page page;

    public CataloguePage(Page page) {
        this.page = page;
    }
    @Step("Add product in the cart")
    public void addProductToCart(String productName){
        page.locator(String.format("//a[text()='%s']/parent::*/parent::div//*[text()='Add to cart']",productName)).click();
    }
}
