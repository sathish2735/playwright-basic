package io.common.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class HomePageTest extends BaseTest {


    @Test(groups = {"SMOKE"}, priority = 3, description = "Verify Products in HomePage")
    public void verifyNumberOfProducts() {

        Page page = context.newPage();

        page.navigate("https://www.saucedemo.com/");

        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        page.waitForURL("**/inventory.html");
        Assert.assertEquals(page.url(), "https://www.saucedemo.com/inventory.html");


        Locator products = page.locator("//*[@data-test='inventory-item']");
        Assert.assertEquals(products.count(), 6);
        log.info("Count of Products on the Homepage is matching with expected value");

    }


}
