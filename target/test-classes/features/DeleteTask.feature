Feature: Delete Task
	This feature verifies the functionality of task deletion
	(As a student, I remove an unnecessary task from my course to do list, so I can forget about it. )

	Background:
		Given I am a student
		And I have a course (project)
		And I have a course todo list

	Scenario: Student delete a task from my course to do list
		Given I have a task under my course(project) todo list
		When I delete the task
		Then I verify that the task is removed

	Scenario: Student delete tasks from an empty my course to do list
		Given I have no task under my course(project) todo list
		When I delete the task
		Then I shall be notified that the task has already removed

	Scenario: Student delete a non-existing task from my course to do list
		Given I have a task under my course (project) todo list
		When I delete the task
		Then I shall be notified that the task has already removed