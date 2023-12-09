package nisfappui.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import nisfappui.tests.BaseTest;

import java.awt.*;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;



public class BrowserContextFactory {

    public static BrowserContext setupBrowserContext(Browser browser, boolean isTraceEnabled) {
        BrowserContext browserContext;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        if (isTraceEnabled) {
            try {
                browserContext = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(width, height)
                        //.setPermissions(Arrays.asList("notifications", "geolocation"))
                        .setRecordVideoDir(Paths.get("videos/"))
                        .setRecordVideoSize(1280, 720)
                        .setStorageStatePath(Paths.get("auth.json")));
            } catch (Exception e) {
                browserContext = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(width, height)
                        //.setPermissions(Arrays.asList("notifications", "geolocation"))
                        .setRecordVideoDir(Paths.get("videos/"))
                        .setRecordVideoSize(1280, 720));
            }

            browserContext.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(false));
        } else {
            try {
                browserContext = browser.newContext(new Browser.NewContextOptions()
                        .setStorageStatePath(Paths.get("auth.json"))
                        //.setPermissions(Arrays.asList("notifications", "geolocation"))
                        .setViewportSize(width, height));
            } catch (Exception e) {
                browserContext = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(width, height));
            }
        }
        return browserContext;
    }
}
