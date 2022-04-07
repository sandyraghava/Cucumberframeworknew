

Feature: Amazon Search
 


  Scenario: Amazon Search
    Given I am on Amazon home page
    When  search box is available search product with name
    Then product displayed click on add to cart
    Then compare the added product with expected 

    
    

  