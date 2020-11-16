package testRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinitions {
	

	// ***********************************************
	// Background step definitions
	// ***********************************************
		
	@Given("The system is running")
	public void the_system_is_running() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
	@Given("I have a course \\(project)")
	public void i_have_a_course_project() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
		
	// ***********************************************
	// First Scenario
	// ***********************************************
	
	
	@Given("I have a HIGH incomplete todo list associated with the project")
	public void i_have_a_high_incomplete_todo_list_associated_with_the_project() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("I query for HIGH incomplete todo list tasks")
	public void i_query_for_high_incomplete_todo_list_tasks() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("I verify that the HIGH priority todo list is returned")
	public void i_verify_that_the_HIGH_priority_todo_list_is_returned() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	// ***********************************************
	// Second Scenario
	// ***********************************************
	
	@Given("I have a LOW incomplete todo list associated with the project")
	public void i_have_a_low_incomplete_todo_list_associated_with_the_project() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("I query for LOW incomplete todo list tasks")
	public void i_query_for_low_incomplete_todo_list_tasks() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("I verify that the LOW priority todo list is returned")
	public void i_verify_that_the_low_priority_todo_list_is_returned() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
	
	//***********************************************
	//Third Scenario
	//***********************************************
	
	@Given("I have no todo list associated with the project")
	public void i_have_no_todo_list_associated_with_the_project() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("I query for non-exist HIGH incomplete todo list tasks")
	public void i_query_for_non_exist_high_incomplete_todo_list_tasks() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("I verify that the LOW priority todo list is returned")
	public void i_verify_that_exceptions_is_handled_correctly() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
