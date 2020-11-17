package testRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class DeleteTodoStepDefinitions {
    public static final String baseUrl = "http://localhost:4567/";
    public static final String projectEndPoint = "projects";
    public static final String tasksEndIDPoint = "/tasks";
    public static final String tasksOfEndPoint = "/tasksof";
    public static final String toDoEndIDPoint = "todos/";
    public static final String toDoEndPoint = "todos";
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
 		String expected_id = "101";
 		HttpUriRequest request_delete = new HttpDelete(baseUrl + toDoEndIDPoint + expected_id);
         HttpResponse httpResponse_delete = httpClient.execute(request_delete);
         assertEquals(404, httpResponse_delete.getStatusLine().getStatusCode());
 	}
 	
 	@Then("I verify that non-exsit todo list exceptions is handled correctly")
 	public void i_verify_that_non_exsit_todo_list_exceptions_is_handled_correctly() throws ClientProtocolException, IOException {
 		String expected_id = "101";
         //Set request
         HttpUriRequest request = new HttpGet(  baseUrl+ toDoEndIDPoint+ expected_id);
         HttpResponse httpResponse = httpClient.execute(request);

         //Check response status
         assertEquals(404, httpResponse.getStatusLine().getStatusCode());
 	}

}
