This repo contains a basic Test automation framework using Playwright with Java, TestNG and Maven. Used example of [saucedemo](https://www.saucedemo.com/) as the AUT.

Playwright Locators:  
1. Xpath  
   **Example**: page.locator("//input[@id='username']").fill("admin");
2. ID Selector  
   **Ex**:-  page.locator("#loginForm").click();  
3. Class Selector  
   **Ex**:- page.locator(".menu-item.active").click();  
4. Text Selector  
   **Ex**:- page.locator("text=Login").click();
5. getByPlaceholder()  
   **Ex**:- page.getByPlaceholder("Search").fill("Playwright");
6. getByLabel()  
   **Ex**:- page.getByLabel("Username").fill("admin");


   
