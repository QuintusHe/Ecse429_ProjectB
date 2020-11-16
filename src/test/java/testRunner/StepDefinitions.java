package testRunner;

import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StepDefinitions {
	
	
	public static final String baseUrl = "http://localhost:4567/";
    public static final String projectEndPoint = "projects";
    public static final String tasksEndIDPoint = "/tasks";
    public static final String categoriesEndPoint = "/categories";
    public JSONParser jsonParser = new JSONParser();
    public HttpClient httpClient = HttpClientBuilder.create().build();

    final String id = "id";
    final String description = "description";
    final String title = "title";
    final String status = "doneStatus";
    final String todos = "todos";
    final String categories = "categories";
    final String project ="projects";
    final String completed = "completed";
    final String active = "active";
    static HttpURLConnection connection;
	

	// ***********************************************
	// Background step definitions
	// ***********************************************
		
	@Given("The system is running")
	public void the_system_is_running() throws Exception {
	   try{
            URL url = new URL(baseUrl);
            connection= (HttpURLConnection) url.openConnection();
            connection.connect();
            assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
        }
        catch(Exception e){
            System.out.println("Error in conneciton");
            throw new Exception();
        }
	    
	}
	
	
	@Given("I have a course \\(project)")
	public void i_have_a_course_project() throws ClientProtocolException, IOException {
		//Set request
        HttpUriRequest request = new HttpGet(  baseUrl+ projectEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);

        //Check response status
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        try{
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray projects_list = (JSONArray) response_jason.get(project);

            int todos_list_size = projects_list.size();
            //check size
            assertTrue(todos_list_size >= 1);

        }
        catch(Exception PasrException){
            System.out.println("Failure");
        }
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
	@Then("I verify that exceptions is handled correctly")
	public void i_verify_that_exceptions_is_handled_correctly() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
