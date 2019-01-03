Feature: Login 
	As a user, when I go to the login page,
	I should be able to login

Scenario: login as teacher 
	Given the user is on the login page 
	When the user logs in as a teacher 
	Then the user should be logged in 
	
	
@login	 
Scenario: login as a team lead 
	Given the user is on the login page 
	When the user logs in as a team lead 
	Then the user should be logged in 
	
	
	