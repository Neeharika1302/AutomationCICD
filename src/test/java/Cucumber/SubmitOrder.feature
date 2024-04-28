@tag
  Feature: Purchase the order from Ecommerce website
    I want to use this template for my feature file
    Background:
      Given I landed on Ecommerce page

    @Regression
    Scenario Outline:Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When I add the product <prodName> to cart
      And Checkout <prodName> and Submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
      Examples:
        | name                    | password    | prodName      |
        |pneeharika2000@gmail.com | Honey@1302  | IPHONE 13 PRO |