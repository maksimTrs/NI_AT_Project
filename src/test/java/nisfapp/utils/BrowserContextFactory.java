package nisfapp.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;

import java.awt.*;
import java.nio.file.Paths;

public class BrowserContextFactory {

    public static BrowserContext setupBrowserContext(Browser browser, boolean isTraceEnabled) {
        BrowserContext browserContext;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        if (isTraceEnabled) {
            browserContext = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(width, height)
                    //.setPermissions(Arrays.asList("notifications", "geolocation"))
                    .setRecordVideoDir(Paths.get("videos/"))
                    .setRecordVideoSize(1280, 720));

            browserContext.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(false));
        } else {
            browserContext = browser.newContext(new Browser.NewContextOptions()
                    //.setPermissions(Arrays.asList("notifications", "geolocation"))
                    .setViewportSize(width, height));
        }
        return browserContext;
    }
}
