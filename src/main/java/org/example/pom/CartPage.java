package org.example.pom;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import java.io.LineNumberReader;
import java.util.List;

public class CartPage {
    private Page page;
    private Locator totalPriceForEachItem;
    private Locator totalCartPrice;

    public CartPage(Page page) {
        this.page = page;
        this.totalPriceForEachItem = page.locator("//tbody[@id='cart-list']//tr[@class='item']//td[last()-1]");
        this.totalCartPrice = page.locator("//table//th[@id='cartTotal']");
    }

    @Step("Get total cart amount")
    @Description("Getting total cart amount without Shipping and handling")
    public double getTotalCartPrice() {
        return Double.parseDouble(totalCartPrice.innerText().replace("$", ""));
    }

    @Step("counting the sum of all goods and returning the price as a response")
    public double countAllItemsPricesInTotal() {
        double price = 0.0;

        for (int i = 0; i < totalPriceForEachItem.count(); i++)
            price += Double.parseDouble(totalPriceForEachItem.nth(i).innerText().replace("$", ""));
        return price;
    }
}
