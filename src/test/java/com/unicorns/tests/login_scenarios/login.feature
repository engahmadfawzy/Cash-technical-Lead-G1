Feature: Login

  Scenario Outline: Login
    Given user click on profile button
    When user click on login button
    And user login with email "<email>" and password "<password>"

    Then validate that logout button is visible on home page

    Examples:
      | email             |password|
      | ahmed7@gmail.com  |Gmail@12|
