package nisfapp.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {
    //.launch(new BrowserType.LaunchOptions().setHeadless(Boolean.getBoolean("HEADLESS_MODE")).setChannel("chrome"));
    public static Browser launchBrowser(Playwright playwright, String browserType, boolean isHeadlessMode) {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadlessMode);
        BrowserType.LaunchOptions launchOptions2 = new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(isHeadlessMode);
        BrowserType.LaunchOptions launchOptions3 = new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(isHeadlessMode);

        Browser browser = switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(launchOptions);
            case "webkit" -> playwright.webkit().launch(launchOptions);
            case "msedge" -> playwright.chromium().launch(launchOptions3);
            default -> playwright.chromium().launch(launchOptions2);
        };
        return browser;
    }
}
