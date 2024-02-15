
@tag
Feature: Purchase the order from ecommerce website

Background: 
Given I landed on Ecommerce page


  @SubmitOrder
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." Message is displayed on Confirmation Page

    Examples: 
      | name                 | password     | productName  |
      | punekar435@gmail.com | Punekar@435  | ZARA COAT 3  |
      
