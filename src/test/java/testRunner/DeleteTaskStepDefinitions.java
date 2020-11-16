package testRunner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertTrue;

public class DeleteTaskStepDefinitions extends FeatureStepDefinitions{


    // ***********************************************
    // Background step definitions
    // ***********************************************
    @Given("I have no task under my course\\(project) todo list")
    public void iHaveNoTaskUnderMyCourseProjectTodoList() {
        String expected_id = "1";
        try{
            JSONObject response_jason1 = send_delete_request(toDoEndIDPoint + expected_id + tasksOfEndPoint +'/'+expected_id , 200);
            JSONObject response_jason = send_request(toDoEndIDPoint+ expected_id+tasksOfEndPoint, 200);
            JSONArray todos_task_list = (JSONArray) response_jason.get(project);
            assertTrue( todos_task_list.size() == 0);

        }
        catch(Exception PasrException){
            System.out.println("Failure");
        }
    }

    @Given("I have a task under my course\\(project) todo list")
    public void iHaveATaskUnderMyCourseProjectTodoList() {
        String expected_id = "1";
        try{
            JSONObject response_jason = send_request(toDoEndIDPoint+ expected_id+tasksOfEndPoint, 200);
            JSONArray todos_task_list = (JSONArray) response_jason.get(project);
            assertTrue(todos_task_list.size() >= 1);

        }
        catch(Exception PasrException){
            System.out.println("Failure");
        }
    }

    // ***********************************************
    // DeleteTaskStep Normal/Alternate Flow
    // ***********************************************
    @When("I delete the task")
    public void iDeleteTheTask() {
        String expected_id = "1";
        try{
            JSONObject response_jason = send_delete_request(toDoEndIDPoint + expected_id + tasksOfEndPoint + '/'+ expected_id, 0);
        }catch(Exception e){
            System.out.println("Failure");
        }
    }

    @Then("I verify that the task is removed")
    public void iVerifyThatTheTaskIsRemoved() {
        String expected_id = "1";
        try{
            JSONObject response_jason = send_request(toDoEndIDPoint+ expected_id+tasksOfEndPoint, 200);
            JSONArray todos_task_list = (JSONArray) response_jason.get(categories);
            assertTrue(todos_task_list.size() == 0);

        }
        catch(Exception PasrException){
            System.out.println("Failure");
        }
    }

    // ***********************************************
    // DeleteTaskStep Error Flow
    // ***********************************************

    @Then("I shall be notified that the task has already removed")
    public void iShallBeNotifiedThatTheTaskHasAlreadyRemoved() {
        String expected_id = "1";
        try{
            JSONObject response_jason = send_delete_request(toDoEndIDPoint + expected_id + tasksOfEndPoint + '/'+ expected_id, 404);
        }
        catch(Exception PasrException){
            System.out.println("Failure");
        }

    }


}
