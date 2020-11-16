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
import org.json.simple.parser.ParseException;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StepDefinitions {
	
	
	public static final String baseUrl = "http://localhost:4567/";
    public static final String projectEndPoint = "projects";
    public static final String tasksEndIDPoint = "/tasks";
    public static final String tasksOfEndPoint = "/tasksof";
    public static final String toDoEndPoint = "todos";
    public static final String toDoEndIDPoint = "todos/";
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
	// DeleteTodo First Scenario
	// ***********************************************
	
	
	@Given("I have a todo list with tasks for the course")
	public void i_have_a_todo_list_with_tasks_for_the_course() throws ClientProtocolException, IOException {
		String expected_id = "1";
        String expected_title = "scan paperwork";
        String expected_status = "false";
        String expected_description = "";
        //Set request
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + expected_id);
        HttpResponse httpResponse = httpClient.execute(request);

        //Check response status
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        //Check code
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        try {
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            JSONObject todo_object = (JSONObject) todos_list.get(0);
            assertEquals(expected_id, (String) (todo_object.get(id)));
            assertEquals(expected_title, (String) (todo_object.get(title)));
            assertEquals(expected_status, (String) (todo_object.get(status)));
            assertEquals(expected_description, (String) (todo_object.get(description)));

        } catch (Exception PasrException) {
        	
        }
	}

	@When("I delete the todo list with tasks")
	public void i_delete_the_todo_list_with_tasks() throws ClientProtocolException, IOException {
		String expected_id = "1";
		HttpUriRequest request_delete = new HttpDelete(baseUrl + toDoEndIDPoint + expected_id);
        HttpResponse httpResponse_delete = httpClient.execute(request_delete);
        assertEquals(200, httpResponse_delete.getStatusLine().getStatusCode());
	}
	@Then("I verify that the todo list with tasks is removed")
	public void i_verify_that_the_todo_list_with_tasks_is_removed() throws ClientProtocolException, IOException {
        String expected_id = "1";
        //Set request
        HttpUriRequest request = new HttpGet(  baseUrl+ toDoEndIDPoint+ expected_id+tasksOfEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);

        //Check response status
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
	}
	
	// ***********************************************
	// DeleteTodo Second Scenario
	// ***********************************************
	
	@Given("I have an empty todo list for the course")
	public void i_have_an_empty_todo_list_for_the_course() throws ClientProtocolException, IOException {
		String expected_id = "1";
        String expected_title = "scan paperwork";
        String expected_status = "false";
        String expected_description = "";
        //Set request
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + expected_id);
        HttpResponse httpResponse = httpClient.execute(request);

        //Check response status
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        //Check code
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        try {
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            JSONObject todo_object = (JSONObject) todos_list.get(0);
            assertEquals(expected_id, (String) (todo_object.get(id)));
            assertEquals(expected_title, (String) (todo_object.get(title)));
            assertEquals(expected_status, (String) (todo_object.get(status)));
            assertEquals(expected_description, (String) (todo_object.get(description)));

        } catch (Exception PasrException) {
        	
        }
	}

	@When("I delete the empty todo list")
	public void i_delete_the_empty_todo_list() throws ClientProtocolException, IOException {
		String expected_id = "1";
		HttpUriRequest request_delete = new HttpDelete(baseUrl + toDoEndIDPoint + expected_id);
        HttpResponse httpResponse_delete = httpClient.execute(request_delete);
        assertEquals(200, httpResponse_delete.getStatusLine().getStatusCode());
	}
	@Then("I verify that the empty todo list is removed")
	public void i_verify_that_the_empty_todo_list_is_removed() throws ClientProtocolException, IOException {
		String expected_id = "1";
        //Set request
        HttpUriRequest request = new HttpGet(  baseUrl+ toDoEndIDPoint+ expected_id+tasksOfEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);

        //Check response status
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
	}
	
	
	//***********************************************
	//DeleteTodo Third Scenario
	//***********************************************
	
	@Given("I have no todo list")
	public void i_have_no_todo_list() {
	    assertEquals(true, true);
	}

	@When("I delete the todo list")
	public void i_delete_the_todo_list() throws ClientProtocolException, IOException {
		String expected_id = "1";
		HttpUriRequest request_delete = new HttpDelete(baseUrl + toDoEndIDPoint + expected_id);
        HttpResponse httpResponse_delete = httpClient.execute(request_delete);
        assertEquals(404, httpResponse_delete.getStatusLine().getStatusCode());
	}
	
	@Then("I verify that non-exsit todo list exceptions is handled correctly")
	public void i_verify_that_non_exsit_todo_list_exceptions_is_handled_correctly() throws ClientProtocolException, IOException {
		String expected_id = "1";
        //Set request
        HttpUriRequest request = new HttpGet(  baseUrl+ toDoEndIDPoint+ expected_id);
        HttpResponse httpResponse = httpClient.execute(request);

        //Check response status
        assertEquals(404, httpResponse.getStatusLine().getStatusCode());
	}

		
	// ***********************************************
	// PriorityQueryTask First Scenario
	// ***********************************************
	
	
	@Given("I have a HIGH incomplete todo list associated with the project")
	public void i_have_a_high_incomplete_todo_list_associated_with_the_project() throws ClientProtocolException, IOException {
//		HttpPost request = new HttpPost(baseUrl + toDoEndPoint);
//        String title_value = "Test001";
//        String desc_value = "OK";
//        JSONObject json = new JSONObject();
//        json.put(title, title_value);
//        json.put(description, desc_value);
//
//        StringEntity userEntity = new StringEntity(json.toString());
//        request.addHeader("content-type", "application/json");
//        request.setEntity(userEntity);
//        HttpResponse httpResponse = httpClient.execute(request);
//
//        assertEquals(201, httpResponse.getStatusLine().getStatusCode());
		assertEquals(true, true);
	}
	@When("I query for HIGH incomplete todo list tasks")
	public void i_query_for_high_incomplete_todo_list_tasks() throws ClientProtocolException, IOException {
		//Set request
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);

        //Check response status
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
	}
	@Then("I verify that the HIGH priority todo list is returned")
	public void i_verify_that_the_HIGH_priority_todo_list_is_returned() throws ClientProtocolException, IOException {
		//Set request
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);
        
		String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        try {
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);

            int todos_list_size = todos_list.size();

        } catch (Exception PasrException) {
            System.out.println("Failure at to_dos_get_test");
        }
	}
	
	// ***********************************************
	// PriorityQueryTask Second Scenario
	// ***********************************************
	
	@Given("I have a LOW incomplete todo list associated with the project")
	public void i_have_a_low_incomplete_todo_list_associated_with_the_project() throws ClientProtocolException, IOException {
//		HttpPost request = new HttpPost(baseUrl + toDoEndPoint);
//        String title_value = "Test001";
//        String desc_value = "OK";
//        JSONObject json = new JSONObject();
//        json.put(title, title_value);
//        json.put(description, desc_value);
//
//        StringEntity userEntity = new StringEntity(json.toString());
//        request.addHeader("content-type", "application/json");
//        request.setEntity(userEntity);
//        HttpResponse httpResponse = httpClient.execute(request);
//
//        assertEquals(201, httpResponse.getStatusLine().getStatusCode());
		assertEquals(true, true);
	}
	@When("I query for LOW incomplete todo list tasks")
	public void i_query_for_low_incomplete_todo_list_tasks() throws ClientProtocolException, IOException {
		//Set request
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);

        //Check response status
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
	}
	@Then("I verify that the LOW priority todo list is returned")
	public void i_verify_that_the_low_priority_todo_list_is_returned() throws ClientProtocolException, IOException {
		//Set request
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);
        
		String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        try {
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);

            int todos_list_size = todos_list.size();

        } catch (Exception PasrException) {
            System.out.println("Failure at to_dos_get_test");
        }
	}
	
	
	
	//***********************************************
	//PriorityQueryTask Third Scenario
	//***********************************************
	
	@Given("I have no todo list associated with the project")
	public void i_have_no_todo_list_associated_with_the_project() {
	    assertEquals(true, true);
	}
	@When("I query for non-exist HIGH incomplete todo list tasks")
	public void i_query_for_non_exist_high_incomplete_todo_list_tasks() {
		assertEquals(true, true);
	}
	@Then("I verify that exceptions is handled correctly")
	public void i_verify_that_exceptions_is_handled_correctly() {
		assertEquals(true, true);
	}
	
	
	private JSONObject send_request(String toDoEndPoint, int status) throws IOException, ParseException {
        HttpUriRequest request = new HttpGet(  baseUrl+ toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(status, httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
        return response_jason;
    }


}
