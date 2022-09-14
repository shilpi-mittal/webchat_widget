Feature: Validate Location Widget

  Background:
    Given I am on Home Page

  @acceptance_test @smoke
  Scenario: Validate opening and closing Location widget
    Then Widget should not be displayed
    When I click on WebChat Widget
    Then I should see Widget
    And I should see Location Widget
    When I close WebChat Widget
    Then Widget should not be displayed

  @acceptance_test
  Scenario: Validate Location widget's static content
    When I click on WebChat Widget
    Then I should see Location Widget
    And should see title as "Select Location"
    And should see subtitle as "Search for the location you want to contact by entering a postal code or address."
    And should see Location search input placeholder as "ZIP Code or address"
    And should see Terms text as "use is subject to terms" and link as "https://www.podium.com/acceptable-use-policy/"
    And should see Location list with 3 expected items
    And should see locations in list as:
      | Scoreboard Sports - Orem\n765 West State Road, American Fork, UT 84003, United States        |
      | Scoreboard Sports - Bountiful\n1402 E Main St, Lehi, UT 84043, United States                 |
      | Scoreboard Sports - Narnia\n6680 Little Cottonwood Canyon Rd, Sandy, UT 84092, United States |
    And I close WebChat Widget

  @acceptance_test @smoke
  Scenario Outline: Validate location search
    When I click on WebChat Widget
    Then I should see Location Widget
    When I enter location search input as "<input>"
    Then I should see first item in list contains "<input>"
    And should see Location list with 3 expected items
    Then I should see Location search input as "<input>"
    And should see Location search icon and clear button
    And should see locations in list as:
      | Scoreboard Sports - Orem\n765 West State Road, American Fork, UT 84003, United States        |
      | Scoreboard Sports - Bountiful\n1402 E Main St, Lehi, UT 84043, United States                 |
      | Scoreboard Sports - Narnia\n6680 Little Cottonwood Canyon Rd, Sandy, UT 84092, United States |
    And should see arrow icon on hover
    When I click on first location
    Then I should see message compose widget
    And message widget header should have "<input>" in title

    Examples:
      | input   |
      | Orem    |
      | Narnia  |
