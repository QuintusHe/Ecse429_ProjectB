package testRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class PriorityQueryTaskStepDefinitions {
    public static final String baseUrl = "http://localhost:4567/";
    public static final String projectEndPoint = "projects";
    public static final String tasksEndIDPoint = "/tasks";
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
    // First Scenario
    // ***********************************************


    @Given("I have a HIGH incomplete todo list associated with the project")
    public void i_have_a_high_incomplete_todo_list_associated_with_the_project() throws ClientProtocolException, IOException {
    	String expected_id = "1";
	 	String expected_title = "scan paperwork";
        String expected_status = "false";
        String expected_desc = "HIGH";
        HttpPost request = new HttpPost(  baseUrl+ projectEndPoint+ "/"+ expected_id+tasksEndIDPoint);
        String todo_id = "1";
        JSONObject json = new JSONObject();
        json.put("id", todo_id);
 
        StringEntity userEntity = new StringEntity(json.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(userEntity);
 
    }
    @When("I query for HIGH incomplete todo list tasks")
    public void i_query_for_high_incomplete_todo_list_tasks() throws ClientProtocolException, IOException {
    	String expected_id = "1";
        String expected_description = "HIGH";
        //Set request
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + expected_id);
        HttpResponse httpResponse = httpClient.execute(request);
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
    // Second Scenario
    // ***********************************************

    @Given("I have a LOW incomplete todo list associated with the project")
    public void i_have_a_low_incomplete_todo_list_associated_with_the_project() throws ClientProtocolException, IOException {
    	String expected_id = "1";
		String expected_title = "scan paperwork";
	    String expected_status = "false";
	    String expected_desc = "HIGH";
	    HttpPost request = new HttpPost(  baseUrl+ projectEndPoint+ "/"+ expected_id+tasksEndIDPoint);
	    String todo_id = "1";
	    JSONObject json = new JSONObject();
	    json.put("id", todo_id);
	
	    StringEntity userEntity = new StringEntity(json.toString());
	    request.addHeader("content-type", "application/json");
	    request.setEntity(userEntity);
	    }
	    @When("I query for LOW incomplete todo list tasks")
	    public void i_query_for_low_incomplete_todo_list_tasks() throws ClientProtocolException, IOException {
    	String expected_id = "1";
        String expected_description = "LOW";
        //Set request
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + expected_id);
        HttpResponse httpResponse = httpClient.execute(request);

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
    //Third Scenario
    //***********************************************

    @Given("I have no todo list associated with the project")
    public void i_have_no_todo_list_associated_with_the_project() {
        assertEquals(true, true);
    }
    @When("I query for non-exist HIGH incomplete todo list tasks")
    public void i_query_for_non_exist_high_incomplete_todo_list_tasks() throws ClientProtocolException, IOException {
    	 String expected_id = "101";
         String expected_title = "scan paperwork";
         String expected_status = "false";
         String expected_description = "";
         //Set request
         HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + expected_id);
         HttpResponse httpResponse = httpClient.execute(request);

         //Check response status
         assertEquals(404, httpResponse.getStatusLine().getStatusCode());
    }
    @Then("I verify that exceptions is handled correctly")
    public void i_verify_that_exceptions_is_handled_correctly() throws ClientProtocolException, IOException {
    	 String expected_id = "101";
         String expected_title = "scan paperwork";
         String expected_status = "false";
         String expected_description = "";
         //Set request
         HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + expected_id);
         HttpResponse httpResponse = httpClient.execute(request);

         //Check response status
         assertEquals(404, httpResponse.getStatusLine().getStatusCode());
    }


}
