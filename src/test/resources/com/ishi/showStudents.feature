Feature: Show list of all the students
	Scenario: students tab shows all the students on the page
	
		Given user is on homepage
		When user clicks Students from navigation bar
		And user clicks showStudents button
		Then list of all students should be shown