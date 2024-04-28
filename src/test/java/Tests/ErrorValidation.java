package Tests;

import POM.CartPage;
import POM.CatalogPage;
import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test(dataProvider="getData",groups = "LoginError")
    public void LoginErrorValidation(String email,String password) {
        landingPage.loginApplication(email, password);
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }
    @Test(retryAnalyzer = Retry.class)
    public void productErrorValidation() {
        String email = "pneeharika2000@gmail.com";
        String password = "Honey@1302";
        String prodName = "IPHONE 13 PRO";
        CatalogPage catalogPage = landingPage.loginApplication(email, password);
        catalogPage.addProductToCart(prodName);
        CartPage cartPage = catalogPage.goToCartPage();
        cartPage.prodMatches("Iphone 13 Pro");
    }
    @DataProvider
    public Object[][] getData() throws IOException {
         return new Object[][] {{"pneeharika2000@gmail.com","Honey@"}};

    }
}

