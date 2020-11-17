package testRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class EditTaskDescriptionStepDefinitions {
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
 	// EditTaskDescription First Scenario
 	// ***********************************************
 		
 		
 	@Given("I have a task with description")
 	public void i_have_a_task_with_description() throws ClientProtocolException, IOException {
 		String expected_id = "2";
         //String expected_title = "file paperwork";
 		String expected_des = "Test_description";
         HttpPost request = new HttpPost(baseUrl + toDoEndIDPoint + expected_id);
         String title_value = "Test_cat_001";
         JSONObject json = new JSONObject();
         json.put(title, title_value);
         json.put(description, expected_des);
         StringEntity userEntity = new StringEntity(json.toString());
         request.addHeader("content-type", "application/json");
         request.setEntity(userEntity);
//         HttpResponse httpResponse = httpClient.execute(request);
//         
//         assertEquals(200, httpResponse.getStatusLine().getStatusCode());

 	}

 	@When("I changed the description of the task")
 	public void i_changed_the_description_of_the_task() throws ClientProtocolException, IOException {
 		String expected_id = "2";
 	    HttpPost request = new HttpPost(baseUrl + toDoEndIDPoint + expected_id );
 	    String new_des = "Change description";
 	    JSONObject json = new JSONObject();
 	    json.put(description, new_des);
 	    StringEntity userEntity = new StringEntity(json.toString());
 	    request.addHeader("content-type", "application/json");
 	    request.setEntity(userEntity);
 	    HttpResponse httpResponse = httpClient.execute(request);

         assertEquals(200, httpResponse.getStatusLine().getStatusCode());
 	}
 	@Then("I verify that the task's description is changed")
 	public void i_verify_that_the_task_s_description_is_changed() throws ClientProtocolException, IOException {
 		String expected_id = "2";
         String expected_title = "Test_cat_001";
         String expected_description = "Change description";
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
             assertEquals(expected_description, (String) (todo_object.get(description)));

         } catch (Exception PasrException) {
             System.out.println("Failure");
 	    }
 	}
 		
 	// ***********************************************
 	// EditTaskDescription Second Scenario
 	// ***********************************************
 		

 	@Given("I have a task without description")
 	public void i_have_a_task_without_description() throws UnsupportedEncodingException {
 		String expected_id = "2";
         //String expected_title = "file paperwork";
 		String expected_des = "";
         HttpPost request = new HttpPost(baseUrl + toDoEndIDPoint + expected_id);
         String title_value = "Test_cat_001";
         JSONObject json = new JSONObject();
         json.put(title, title_value);
         json.put(description, expected_des);
         StringEntity userEntity = new StringEntity(json.toString());
         request.addHeader("content-type", "application/json");
         request.setEntity(userEntity);
//         HttpResponse httpResponse = httpClient.execute(request);
//         
//         assertEquals(200, httpResponse.getStatusLine().getStatusCode());

 	}
 	
 	@When("I changed the empty description of the task")
 	public void i_changed_the_empty_description_of_the_task() throws ClientProtocolException, IOException {
 		String expected_id = "2";
 	    HttpPost request = new HttpPost(baseUrl + toDoEndIDPoint + expected_id );
 	    String new_des = "Change description";
 	    JSONObject json = new JSONObject();
 	    json.put(description, new_des);
 	    StringEntity userEntity = new StringEntity(json.toString());
 	    request.addHeader("content-type", "application/json");
 	    request.setEntity(userEntity);
 	    HttpResponse httpResponse = httpClient.execute(request);

         assertEquals(200, httpResponse.getStatusLine().getStatusCode());
 	}
 	@Then("I verify that the empty task's description is changed")
 	public void i_verify_that_the_empty_task_s_description_is_changed() throws ClientProtocolException, IOException {
 		String expected_id = "2";
         String expected_title = "Test_cat_001";
         String expected_description = "Change description";
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
             assertEquals(expected_description, (String) (todo_object.get(description)));

         } catch (Exception PasrException) {
             System.out.println("Failure");
 	    }
 	}
 	
 	
 	//***********************************************
 	//EditTaskDescription Third Scenario
 	//***********************************************
 	
 	@Given("I have no task")
 	public void i_have_no_task() {
 	    //no task is created
 	}

 	@When("I changed the description of the non-exist task")
 	public void i_changed_the_description_of_the_non_exist_task() throws ClientProtocolException, IOException {
 		String expected_id = "101";
 	    HttpPost request = new HttpPost(baseUrl + toDoEndIDPoint + expected_id );
 	    String new_des = "Change description";
 	    JSONObject json = new JSONObject();
 	    json.put(description, new_des);
 	    StringEntity userEntity = new StringEntity(json.toString());
 	    request.addHeader("content-type", "application/json");
 	    request.setEntity(userEntity);
 	    HttpResponse httpResponse = httpClient.execute(request);

         assertEquals(404, httpResponse.getStatusLine().getStatusCode());
 	}
 	@Then("I verify that description editing exceptions is handled correctly")
 	public void i_verify_that_description_editing_exceptions_is_handled_correctly() throws ClientProtocolException, IOException {
 		String expected_id = "101";
         String expected_title = "Test_cat_001";
         String expected_description = "Change description";
         //Set request
         HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + expected_id);
         HttpResponse httpResponse = httpClient.execute(request);
         
       //Check response status
         assertEquals(404, httpResponse.getStatusLine().getStatusCode());

 	}

}
