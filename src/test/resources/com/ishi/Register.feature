Feature: Student Registration

	Scenario: Successful registration with student details
	
		Given user is on registration page
		When student fills his/her details
		Then student should be shown the message registration successful
		