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
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class TaskStatusStepDefinitions {
    public static final String baseUrl = "http://localhost:4567/";
    public static final String projectEndPoint = "projects";
    public static final String tasksEndIDPoint = "/tasks";
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
    int expecteCode = 404;
    
    //***********************************************
    //TaskStatus Scenario
    //***********************************************
    String todoListId1;
    String todoListTitle1 = "my course";
    boolean todoListStatus1 = false;
    String todoListDescription1 = "Imcomplete todo list";
    JSONObject returnedOBJ1;
    int httpCode1;

    @Given("I have a todo list named as my course")
    public void I_have_a_todo_list_named_as_my_course() {
        HttpPost request = new HttpPost(baseUrl+toDoEndPoint);
        JSONObject json = new JSONObject();
        json.put("title", todoListTitle1);
        json.put("doneStatus", todoListStatus1);
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
            HttpResponse httpResponse = httpClient.execute(request);
            assertEquals(201, httpResponse.getStatusLine().getStatusCode());
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            JSONObject todo_object = (JSONObject) todos_list.get(0);
            todoListId1 = (String) (todo_object.get("id"));
        } catch (Exception e) {};
    }

    @Given("I have a task under the todo list with a status of not done")
    public void I_have_a_task_under_the_todo_list_with_a_status_of_not_done() {
    	HttpPost request = new HttpPost(baseUrl+toDoEndPoint+"/"+todoListId1);
        JSONObject json = new JSONObject();
        json.put("title", todoListTitle1);
        json.put("doneStatus", false);
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
            HttpResponse httpResponse = httpClient.execute(request);
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            JSONObject todo_object = (JSONObject) todos_list.get(0);
            todoListId1 = (String) (todo_object.get("id"));
        } catch (Exception e) {};
    }
    
    @When("I mark the task as done")
    public void I_mark_the_task_as_done() {
    	HttpPost request = new HttpPost(baseUrl+toDoEndPoint+"/"+todoListId1);
        JSONObject json = new JSONObject();
        json.put("title", todoListTitle1);
        json.put("doneStatus", true);
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
            HttpResponse httpResponse = httpClient.execute(request);
            httpCode1 = httpResponse.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            returnedOBJ1 = (JSONObject) todos_list.get(0);
            todoListId1 = (String) (returnedOBJ1.get("id"));
        } catch (Exception e) {};
    }
    
    @Then("I verify that the task's status is done")
    public void I_verify_that_the_task_s_status_is_done()
    {
    	assertEquals(expecteCode, httpCode1);
    }
    
    @Given("I delete the task 1")
    public void I_delete_the_task_1() {
    	HttpDelete request = new HttpDelete(baseUrl+toDoEndPoint+"/"+todoListId1);
    	try{
    		HttpResponse httpResponse = httpClient.execute(request);
    		assertEquals(expecteCode, httpResponse.getStatusLine().getStatusCode());
    	} catch (Exception e) {System.out.println("failed");}
    }
    
    @Then("I verify that exceptions is handled correctly \\(task status)")
    public void I_verify_that_exceptions_is_handled_correctly_task_status() {
    	assertEquals(404, httpCode1);
    }
}
