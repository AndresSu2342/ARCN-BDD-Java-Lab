Feature: Dropdown selection on The Internet
  As a user
  I want to be able to select options from a dropdown
  So that I can verify the correct option is selected

  Scenario Outline: Selecting an option from the dropdown
    Given the user is on the dropdown page
    When the user selects option "<optionName>"
    Then the selected option should be "<optionName>"

    Examples:
      | optionName |
      | Option 1   |
      | Option 2   |
