package testRunner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertEquals;

public class CreateTodoStepDefinitions extends FeatureStepDefinitions {

    // ***********************************************
    // CreateTodo Normal Flow
    // ***********************************************


    String expected_id = "1";
    String title_value = "Test_task_001";
    String description_value = "A description";
    String result_title;
    String result_description;
    @When("I create a todo list with a title")
    public void iCreateATodoListWithTitle() {
        try{
            JSONObject json = new JSONObject();
            json.put(title, title_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason = send_post_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 201, userEntity);
            result_title = (String) (response_jason.get(title));
        }
        catch(Exception e){

        }

    }

    @Then("I verify that a new todo list is created with the title")
    public void iVerifyThatANewTodoListIsCreatedWithTheTitle() {
        assertEquals(title_value, result_title);
    }

    // ***********************************************
    // CreateTodo Alternate Flow
    // ***********************************************

    @When("I create a todo list with a title and description")
    public void iCreateATodoListWithTitleAndDescription() {
        try{
            JSONObject json = new JSONObject();
            json.put(title, title_value);
            json.put(description, description_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason = send_post_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 201, userEntity);
            result_title = (String) (response_jason.get(title));
            result_description = (String) (response_jason.get(description));
        }
        catch(Exception e){

        }
    }

    @Then("I verify that a new todo list is created with the title and the description")
    public void iVerifyThatANewTodoListIsCreatedWithTheTitleAndTheDescription() {
        assertEquals(title_value, result_title);
        assertEquals(description_value, result_description);
    }

    // ***********************************************
    // CreateTodo Error Flow
    // ***********************************************
    StringEntity userEntity;
    @When("I create the a todo list with empty title")
    public void iCreateTheATodoListWithEmptyTitle() {
        try {
            String expected_id = "1";
            userEntity=  new StringEntity("");
            JSONObject response_jason = send_post_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 404, userEntity);
        }
        catch(Exception e){
            System.out.println("Failure at AddTestS3");
        }
        
    }

    @Then("I shall be notified that the creation of to-do is failed")
    public void iShallBeNotifiedThatTheToDoHasAlreadyExisted() {
        try {
            String expected_id = "1";
            StringEntity userEntity=  new StringEntity("");
            JSONObject response_jason = send_post_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 404, userEntity);
        }
        catch(Exception e){
            System.out.println("Failure at AddTestS3");
        }

    }
}
