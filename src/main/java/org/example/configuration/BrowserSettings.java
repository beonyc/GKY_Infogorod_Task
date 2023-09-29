package org.example.configuration;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserSettings {
    private static Browser browser;
    private static Page page;
    private static String browserName = "chrome";

    public static Page getNavigate() {
        switch (browserName) {
            case "chrome":
                browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                page = browser.newPage();
                break;
            case "fireFox":
                browser = Playwright.create().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                page = browser.newPage();
                break;
            case "webkit":
                browser = Playwright.create().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                page = browser.newPage();
                break;
        }
        return page;
    }

}
