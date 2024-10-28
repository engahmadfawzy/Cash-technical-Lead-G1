Feature: Register

  Scenario Outline: Valid Register
    Given user click on profile button
    When user click on register button
    And user fill username "<username>" and email "<email>" and password "<password>" and gender "<gender>"
    Then validate that logout button is visible on home page



    Examples:
      |username|   email           |   password  | gender|
      | Ahmed  | ahmed212@gmail.com|  Test@123   | Male  |
