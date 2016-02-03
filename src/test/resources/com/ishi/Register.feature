Feature: Student Registration

Scenario Outline: Successful registration with student details
		Given user is on homepage
		When user clicks Register from navigation bar
		And student fills "<firstname>" and "<lastname>"
		Then student should be shown the message registration successful

Examples:
	| firstname | lastname |
	| Samip | Kothari |
	| Ankit | Makwana |
	| Brijen | Makwana |
	| Manit | Ankhad |