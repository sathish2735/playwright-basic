package io.common.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class BaseTest {

    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;


    @BeforeSuite
    public void beforeSuite() {
        clearDirectory(System.getProperty("user.dir") + "/videos");
        log.info("Previous run video files have been cleared");

        playwright = Playwright.create();   // Create once per entire suite
    }

    @BeforeMethod
    public void setUp() {

        // Launch a fresh browser for each test
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        // Create a new context for each test
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setIgnoreHTTPSErrors(true)
                        .setRecordVideoDir(Paths.get("videos"))
        );
    }

    @AfterMethod
    public void tearDown() {
        if (context != null) context.close();   // Closes video recording too
        if (browser != null) browser.close();   // Fully close browser
    }

    @AfterSuite
    public void afterSuite() {
        if (playwright != null) playwright.close();   // Shutdown Playwright
    }


    private void clearDirectory(String path) {
        try (var paths = Files.walk(Paths.get(path))) {
            paths.map(java.nio.file.Path::toFile)
                    .sorted((o1, o2) -> -o1.compareTo(o2)) // delete files before directories
                    .forEach(file -> {
                        if (!file.delete()) {
                            log.warn("Failed to delete {}", file.getAbsolutePath());
                        }
                    });
            log.info("Cleared directory: {}", path);
        } catch (IOException e) {
            log.error("Failed to clear directory {}: {}", path, e.getMessage());
        }
    }
}
