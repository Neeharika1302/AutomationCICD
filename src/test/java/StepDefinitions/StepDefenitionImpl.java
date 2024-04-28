package StepDefinitions;

import POM.*;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class StepDefenitionImpl extends BaseTest {
    public LandingPage landingPage;
    public CatalogPage catalogPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public SuccessPage successPage;
    @Given("I landed on Ecommerce page")
    public void i_landed_on_Ecommerce_page() throws IOException {
        landingPage=launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username,String password){
        catalogPage= landingPage.loginApplication(username,password);
    }
    @When("^I add the product (.+) to cart$")
    public void i_add_the_product_to_cart(String prodName){
        catalogPage.addProductToCart(prodName);
    }

    @When("^Checkout (.+) and Submit the order$")
    public void Checkout_and_Submit_the_order(String prodName){
        cartPage=catalogPage.goToCartPage();
        cartPage.prodMatches(prodName);
        checkoutPage=cartPage.goToCheckoutPage();
        checkoutPage.selectCountry();
        successPage=checkoutPage.placeOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_Confirmation_Page(String message){
        String thankyouMsg=successPage.getThankYouText();
        Assert.assertTrue(thankyouMsg.equalsIgnoreCase(message));
        driver.close();
    }
    @Then("{string} message is displayed")
    public void incorrect_email_or_password_message_is_displayed(String errorMessage) {
        Assert.assertEquals(errorMessage,landingPage.getErrorMessage());
        driver.close();
    }
}
