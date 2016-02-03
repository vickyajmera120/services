Feature: Student Registration

	Scenario: Successful registration with student details
	
		Given user is on homepage
		When user clicks Register from navigation bar
		And student fills his/her details
		Then student should be shown the message registration successful
		