Feature: purchase products

  Scenario Outline: Search for the keyboard
    Given user click on profile button
    When user click on login button
    And user login with email "<email>" and password "<password>"
    Then validate that logout button is visible on home page
    When user click on category "<category>" button in categories section
    And  user click on Apply Button
    Then validate that only category "<category>" products appear
    When user click on product "<productName>" in home page
    Then validate that the product price is shown on product page
    Then validate that the cart is empty
    When user click on add to cart button
    Then the product is added to the cart
    When user click on products button
    And user click on category "<category2>" button in categories section
    And  user click on Apply Button
    And user click on product "<productName2>" in home page
    And user click on add to cart button
    And user click on cart button
    Then validate that the two products are successfully added
    Then validate that the total price is correct
    When user click on checkout button
    And user filling the country "<country>" and region "<region>" and address "<address>" and zipcode "<zip>" and phone number "<phoneNumber>" on checkout page
    Then validate that shipping details "<country>" and "<region>" and "<address>" and "<price>" are correct on confirmation page

    Examples:
      | email             |password| category | productName                                               | category2 |productName2                 | country|region|address|zip  |phoneNumber| price |
      | ahmed7@gmail.com  |Gmail@12| keyboard | Logitech Wireless Keyboard and Mouse Combo - Black (MK270)| laptop    |Dell Chromebook 11 3120 (11.6|Egypt   |Cairo |Maddi  |12111|01004427827| 733   |
