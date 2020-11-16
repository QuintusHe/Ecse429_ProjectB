Feature: Add Task
	This feature verifies the functionality of adding a task
	(As a student, I add a task to a course to do list, so I can remember it. )

	Background:
		Given I have connected to the TodoManager Server
		And I have a course (project)
		And I have a todo list associated with the project

	Scenario: Student adds a task to a course (project) to todo list
		When I add a task to the todo list with a specific title
		Then I verify that there is a task in the todo list associated with the project with the title

	Scenario: Student adds a task and its description to a course (project) to todo list
		When I add a task to the todo list with a specific title and a description
		Then I verify that there is a task in the todo list associated with the project with the title and the description

	Scenario: Student adds a task to a non-existing course (project) to todo list
		Given I deleted the project
		When I add a task to the todo list
		Then I shall be notified that the project doesn't exist

	Scenario: Student adds a task to a course (project) with no todo list
		Given I deleted the todo list
		When I add a task to the todo list
		Then I shall be notified that the todo list doesn't exist