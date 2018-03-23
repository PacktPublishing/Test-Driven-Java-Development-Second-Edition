Scenario:  TDD search on wikipedia
 
Given I go to Wikipedia homepage
When I enter the value Test-driven development on a field named search
When I click the button go
Then the page title contains Test-driven development