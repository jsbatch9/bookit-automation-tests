Feature: User account information 

@account
Scenario: Team information 
	Given the user is on the login page 
	And the user logs using "kodonnelly7t@bigcartel.com" and "robertamurrison" 
	When the user goes to myself page 
	Then team name "Django" should be displayed 