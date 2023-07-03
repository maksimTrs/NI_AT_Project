package nisfapp.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {
    //.launch(new BrowserType.LaunchOptions().setHeadless(Boolean.getBoolean("HEADLESS_MODE")).setChannel("chrome"));
    public static Browser launchBrowser(Playwright playwright, String browserType, boolean isHeadlessMode) {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadlessMode);

        Browser browser = switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(launchOptions);
            case "webkit" -> playwright.webkit().launch(launchOptions);
            default -> playwright.chromium().launch(launchOptions);
        };
        return browser;
    }
}
