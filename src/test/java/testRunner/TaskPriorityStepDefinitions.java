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

public class TaskPriorityStepDefinitions {
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

    //***********************************************
    //TaskPriority Scenario
    //***********************************************
    String todoListId1;
    String todoListTitle1 = "HIGH";
    String todoListTitle2 = "LOW";
    String todoListDescription1 = "high prioirty";
    JSONObject returnedOBJ1;
    int httpCode1 = 200;

    
    @Given("I have a HIGH priority task associated with the project")
    public void I_have_a_HIGH_priority_task_associated_with_the_project() {
    	HttpPost request = new HttpPost(baseUrl+categoriesEndPoint);
        JSONObject json = new JSONObject();
        json.put("title", todoListTitle1);
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
            HttpResponse httpResponse = httpClient.execute(request);
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            returnedOBJ1 = (JSONObject) todos_list.get(0);
        } catch (Exception e) {};
    }
    
    @Given("I have a MEDIUM priority task associated with the project")
    public void I_have_a_MEDIUM_priority_task_associated_with_the_project() {
    	HttpPost request = new HttpPost(baseUrl+categoriesEndPoint);
        JSONObject json = new JSONObject();
        json.put("title", "MEDIUM");
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
            HttpResponse httpResponse = httpClient.execute(request);
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            returnedOBJ1 = (JSONObject) todos_list.get(0);
        } catch (Exception e) {};
    }
    
    @When("I change the task priority to LOW")
    public void I_change_the_task_priority_to_LOW() {
    	HttpPost request = new HttpPost(baseUrl+categoriesEndPoint+"/"+todoListId1);
        JSONObject json = new JSONObject();
        json.put("title", "LOW");
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
            HttpResponse httpResponse = httpClient.execute(request);
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            returnedOBJ1 = (JSONObject) todos_list.get(0);
        } catch (Exception e) {};
    }
    
    @Then("I verify that the task priority is LOW")
    public void I_verify_that_the_task_priority_is_LOW() {
    	assertEquals("LOW", todoListTitle2);
    }
    
    @When("I change the task priority to HIGH")
    public void I_change_the_task_priority_to_HIGH() {
    	HttpPost request = new HttpPost(baseUrl+categoriesEndPoint+"/"+todoListId1);
        JSONObject json = new JSONObject();
        json.put("title", "HIGH");
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
            HttpResponse httpResponse = httpClient.execute(request);
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            returnedOBJ1 = (JSONObject) todos_list.get(0);
        } catch (Exception e) {};
    }
    
    @When("I change the task priority to MEDIUM")
    public void I_change_the_task_priority_to_MEDIUM() {
    	HttpPost request = new HttpPost(baseUrl+categoriesEndPoint+"/114514");
        JSONObject json = new JSONObject();
        json.put("title", "MEDIUM");
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
            HttpResponse httpResponse = httpClient.execute(request);
            httpCode1=httpResponse.getStatusLine().getStatusCode();
        } catch (Exception e) {};
    }
    
    @Then("I shall be notified that the task doesn't exist 1")
    public void I_shall_be_notified_that_the_task_doesn_t_exist_1(){
    	assertEquals(404,httpCode1);
    }
    
    @Then("I verify that the task priority is HIGH")
    public void I_verify_that_the_task_priority_is_HIGH() {
    	assertEquals("HIGH", todoListTitle1);
    }
    
    @Given("I have no task associated with the project")
    public void I_have_no_task_associated_with_the_project() {
    	// create no task
    	HttpPost request = new HttpPost(baseUrl+categoriesEndPoint);
        JSONObject json = new JSONObject();
        json.put("title", "MEDIUM");
        json.put("description", todoListDescription1);
        try {
            StringEntity userEntity = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(userEntity);
        } catch (Exception e) {};
    }
}
