package Tests;

import POM.*;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAloneTestPOM extends BaseTest{
        @Test(dataProvider="getData",groups = "PurchaseOrder")
        public void submitOrder(HashMap<String,String> input) throws IOException {
            CatalogPage catalogPage = landingPage.loginApplication(input.get("email"), input.get("password"));
            catalogPage.addProductToCart(input.get("prodName"));
            CartPage cartPage = catalogPage.goToCartPage();
            cartPage.prodMatches(input.get("prodName"));
            CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
            checkoutPage.selectCountry();
            SuccessPage successPage = checkoutPage.placeOrder();
            String thankyouMsg = successPage.getThankYouText();
            Assert.assertTrue(thankyouMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        }
        @Test(dependsOnMethods = "submitOrder",dataProvider = "getData")
        public void orderHistory(HashMap<String,String> input){
            landingPage.loginApplication(input.get("email"),input.get("password"));
            OrdersPage ordersPage=landingPage.goTOOrdersPage();
            Boolean match=ordersPage.verifyOrderDisplay(input.get("prodName"));
            Assert.assertTrue(match);
        }
        @DataProvider
        public Object[][] getData() throws IOException {
          //  return new Object[][] {{"pneeharika2000@gmail.com","Honey@1302","IPHONE 13 PRO"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
            List<HashMap<String, String>> mapList= getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Data//PurchaseOrder.Json");
            return new Object[][] {{mapList.get(0)},{mapList.get(1)}};
        }
}