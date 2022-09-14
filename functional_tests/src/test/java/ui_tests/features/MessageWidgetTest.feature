Feature: Validate Message Widget

  Background:
    Given I am on Home Page
    And I click on WebChat Widget
    Then I should see Location Widget

  @acceptance_test @smoke
  Scenario: Validate opening and closing message widget
    When I click on first location
    Then I should see message compose widget
    When I close WebChat Widget
    Then Widget should not be displayed
    When I click on WebChat Widget
    Then I should see Location Widget

  @acceptance_test @smoke
  Scenario: Validate Message widget's content
    When I enter location search input as "Orem"
    And I click on first location
    Then I should see message compose widget
    And should see current location name as "Scoreboard Sports - Orem"
    And should see current location address as "765 West State Road, American Fork, UT 84003, United States"
    And should see back button
    And should see text invitation with message containing "intro message -"
    And should see required input field for Name with label as "Name*"
    Then I should not see name checkmark
    And should see input field for Mobile Phone with label as "Mobile Phone*"
    Then I should not see mobile phone checkmark
    And should see input field for Message with label as "Message*"
    And should see inactive Send button
    And I close WebChat Widget

  @acceptance_test
  Scenario Outline: Validate valid invalid Name Inputs
    When I enter location search input as "Orem"
    And I click on first location
    Then I should see message compose widget
    When I enter Name input as "<input>"
    Then I "<checkmark_expectation>" see checkmark against name input field

    Examples:
      | input  | checkmark_expectation |
      |        | should not            |
      | Nina   | should                |
#      | <      | should not            | (Enable when bug # is fixed)
      | Nina 1 | should                |

  @acceptance_test
  Scenario: Validate Only spaces as Name Input
    When I enter location search input as "Orem"
    And I click on first location
    Then I should see message compose widget
    When I enter Name input as "   "
    Then I "should not" see checkmark against name input field

  @acceptance_test
  Scenario Outline: Validate valid invalid Mobile Phone Inputs
    When I enter location search input as "Orem"
    And I click on first location
    Then I should see message compose widget
    When I enter Mobile Phone input as "<input>"
    Then I should see Mobile Phone input as "<expected_input>"
    And I "<checkmark_expectation>" see checkmark against mobile phone input field
    And I "<flag_expectation>" see flag picker

    Examples:
      | input               | checkmark_expectation | flag_expectation | expected_input       |
      |                     | should not            | should           |                      |
      | 1                   | should not            | should           | 1                    |
      | 12345               | should not            | should           | 1 (234) 5            |
      | abc                 | should not            | should           |                      |
      | 2434567891          | should                | should           | (243) 456-7891       |
      | +11234567891        | should                | should not       | +1 1 234 567 891     |
      | +112345678917856902 | should not            | should not       | +1 12345678917856902 |
      | 22345678917856902   | should not            | should           | 22345678917856902    |
      | +913212346785       | should                | should not       | +91 3212 346 785     |

  @acceptance_test
  Scenario Outline: Validate send button on valid invalid inputs combinations
    When I enter location search input as "Orem"
    And I click on first location
    Then I should see message compose widget
    When I enter Name input as "<name>"
    And I enter Mobile Phone input as "<phone>"
    And I enter Message input as "<message>"
    Then I should see Send button "<status>"

    Examples:
      | name | phone              | message | status   |
      | Nina | 2434567891         | hi      | enabled  |
      |      | 2434567891         | hi      | disabled |
      | Nina | 2434567891         |         | disabled |
      | Nina | 2434               | hi      | disabled |
      | Nina |                    | hi      | disabled |
      | Nina | 243456789112345678 | hi      | disabled |

  @acceptance_test
  Scenario: Validate send button when only spaces in all text input fields
    When I enter location search input as "Orem"
    And I click on first location
    Then I should see message compose widget
    When I enter Name input as "  "
    And I enter Mobile Phone input as "123"
    And I enter Message input as "  "
    Then I should see Send button "disabled"

  @acceptance_test @smoke
  Scenario: Validate back button and data retention
    When I enter location search input as "Orem"
    And I click on first location
    Then I should see message compose widget
    When I enter Name input as "Nina"
    And I enter Mobile Phone input as "2434567891"
    And I enter Message input as "hi"
    And click on back button
    Then I should see Location Widget
    And I should see Location search input as "Orem"
    When I click on first location
    Then I should see message compose widget
    And I should see Name input as "Nina"
    And I "should" see checkmark against name input field
    And I should see Mobile Phone input as "(243) 456-7891"
    And I "should" see checkmark against mobile phone input field
    And I should see Message input as "hi"

  @acceptance_test
  Scenario: Validate data retention should not retain after closing the widget
    When I enter location search input as "Orem"
    And I click on first location
    Then I should see message compose widget
    When I enter Name input as "Nina"
    And I enter Mobile Phone input as "2434567891"
    And I enter Message input as "hi"
    And I close WebChat Widget
    And I click on WebChat Widget
    Then I should see Location Widget
    When I enter location search input as "Orem"
    And I should see Location search input as ""
    And I click on first location
    Then I should see message compose widget
    And I should see Name input as ""
    And I "should not" see checkmark against name input field
    And I should see Mobile Phone input as ""
    And I "should not" see checkmark against mobile phone input field
    And I should see Message input as ""
