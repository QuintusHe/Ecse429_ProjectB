Feature: Task Status
	This feature verifies the functionality of task status
	(As a student, I mark a task as done on my course to do list, so I can track my accomplishments.)
	Background:
		Given I have connected to the TodoManager Server
		Given I have a course (project)
	Scenario: Student changes the status of a task to done
		Given I have a todo list named as my course
		And I have a task under the todo list with a status of not done
		When I mark the task as done
		Then I verify that the task's status is done

	Scenario: Student changes the status of a non-existing task to done
		Given I have a todo list named as my course
		And I have a task under the todo list with a status of not done
		And I delete the task
		When I mark the task as done
		Then I verify that exceptions is handled correctly