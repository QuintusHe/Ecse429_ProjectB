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

public class QueryTaskStepDefinitions {
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
    //QueryTask Scenario
    //***********************************************

    String todoListId;
    String todoListTitle = "todo123";
    boolean todoListStatus = false;
    String todoListDescription = "Imcomplete todo list";
    JSONObject returnedOBJ;
    int httpCode;

    @Given("I have an incomplete todo list associated with the project")
    public void I_have_an_incomplete_todo_list_associated_with_the_project() {
        HttpPost request = new HttpPost(baseUrl+toDoEndPoint);
        JSONObject json = new JSONObject();
        json.put("title", todoListTitle);
        json.put("doneStatus", todoListStatus);
        json.put("description", todoListDescription);
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
            todoListId = (String) (todo_object.get("id"));
        } catch (Exception e) {};
    }

    @Given("I have no incomplete todo list associated with the project")
    public void I_have_no_incomplete_todo_list_associated_with_the_project() {
        HttpPost request = new HttpPost(baseUrl+toDoEndPoint);
        JSONObject json = new JSONObject();
        json.put("title", todoListTitle);
        json.put("doneStatus", true);
        json.put("description", todoListDescription);
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
            todoListId = (String) (todo_object.get("id"));
        } catch (Exception e) {};
    }

    @When("I query for non existing todo list tasks")
    public void I_query_for_non_existing_incomplete_todo_list_tasks() {
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + "114514");
        try {
            HttpResponse httpResponse = httpClient.execute(request);
            httpCode = httpResponse.getStatusLine().getStatusCode();
        } catch (Exception PasrException) {
            System.out.println("Failure at to_dos_get_test");
        }
    }

    @Then("I verify that exceptions is handled correctly 1")
    public void I_verify_that_exceptions_is_handled_correctly_1() {
        assertEquals(httpCode, 404);
    }

    @When("I query for incomplete todo list tasks")
    public void I_query_for_incomplete_todo_list_tasks() {
        HttpUriRequest request = new HttpGet(baseUrl + toDoEndIDPoint + todoListId);
        try {
            HttpResponse httpResponse = httpClient.execute(request);
            httpCode = httpResponse.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
            JSONArray todos_list = (JSONArray) response_jason.get(todos);
            returnedOBJ = (JSONObject) todos_list.get(0);
        } catch (Exception PasrException) {
            System.out.println("Failure at to_dos_get_test");
        }
    }

    @Then("I verify that the right todo list is returned")
    public void I_verify_that_the_right_todo_list_is_returned() {
        try {
            assertEquals(todoListId, (String) returnedOBJ.get("id"));
            assertEquals(todoListTitle, (String) returnedOBJ.get("title"));
            assertEquals(todoListStatus, (String) returnedOBJ.get("doneStatus"));
            assertEquals(todoListDescription, (String) returnedOBJ.get("description"));
        } catch (Exception e) {
            assertEquals(true, false);
        }
    }
}
