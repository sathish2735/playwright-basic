package io.common.tests;
import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class LoginPageTest extends BaseTest {


    @Test(groups = {"SMOKE"}, priority = 1, description = "Verify Login Functionality" +
            " with Invalid credentials")
    public void verifyEmptyUserLogin() {

        Page page = context.newPage();

        page.navigate("https://www.saucedemo.com/");
        Assert.assertEquals(page.title(), "Swag Labs");

        page.locator("#login-button").click();
        Assert.assertTrue(page.content().contains("Epic sadface"));

        log.info("Login Test is successful with invalid credentials");

        synchronized (this) {
            try {
                this.wait(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test(groups = {"REGRESSION"}, priority = 2, description = "Verify Login Functionality" +
            " with Valid credentials")
    public void verifyValidUserLogin() {

        Page page = context.newPage();

        page.navigate("https://www.saucedemo.com/");

        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        page.waitForURL("**/inventory.html");
        Assert.assertEquals(page.url(), "https://www.saucedemo.com/inventory.html");
        log.info("Login Test is successful with Valid credentials");

        synchronized (this) {
            try {
                this.wait(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}