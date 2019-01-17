Feature: Api testing with bookit


Scenario: verify information about logged user
	Given I logged Bookit api using "sbirdbj@fc2.com" and "asenorval"
	When I get the current user information from api 
	Then the information about current user should be returned 
	
 @db	
Scenario: verify information about logged user from api and database
	Given I logged Bookit api using "sbirdbj@fc2.com" and "asenorval"
	When I get the current user information from api 
	Then the information about current user from api and database should be match 
	
	
 @db	
Scenario Outline: verify information about logged user from api and database DDF
	Given I logged Bookit api using "<username>" and "<password>"
	When I get the current user information from api 
	Then the information about current user from api and database should be match

	Examples:
	|username					|password 			|
	|sbirdbj@fc2.com				|asenorval			|
	|efewtrell8c@craigslist.org	|jamesmay         	|
    |jrowesby8h@google.co.uk   	|aldridgegrimsdith	|
    |bmurkus8q@psu.edu          |alicasanbroke     	|
 
 @db
Scenario: three point verification (UI,DATABASE,API)
	Given user logs in using "efewtrell8c@craigslist.org" "jamesmay"
	When user is on the my self page
	And I logged Bookit api using "efewtrell8c@craigslist.org" and "jamesmay"
	And I get the current user information from api
	Then UI,API and Database user information must be match
	
 @db
Scenario Outline: three point verification (UI,DATABASE,API)
	Given user logs in using "<username>" "<password>"
	When user is on the my self page
	And I logged Bookit api using "<username>" and "<password>"
	And I get the current user information from api
	Then UI,API and Database user information must be match
	
Examples:
	|username					|password 			|
	|sbirdbj@fc2.com				|asenorval			|
	|efewtrell8c@craigslist.org	|jamesmay         	|
    |jrowesby8h@google.co.uk   	|aldridgegrimsdith	|
    |bmurkus8q@psu.edu          |alicasanbroke     	|

 






    
    
    
    