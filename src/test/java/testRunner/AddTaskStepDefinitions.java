package testRunner;

import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
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
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
import org.apache.http.HttpResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AddTaskStepDefinitions {


    public static final String baseUrl = "http://localhost:4567/";
    public static final String toDoEndPoint = "todos";
    public static final String toDoEndIDPoint = "todos/";
    public static final String tasksOfEndPoint = "/tasksof";
    public JSONParser jsonParser = new JSONParser();
    public HttpClient httpClient = HttpClientBuilder.create().build();

    final String description = "description";
    final String title = "title";
    final String todos = "todos";
    final String project ="projects";

    // ***********************************************
    // Background step definitions
    // ***********************************************

    @And("I have a todo list associated with the project")
    public void iHaveATodoListAssociatedWithTheProject() {
        try{
            JSONObject response_jason = send_request(toDoEndPoint, 200);
            JSONArray projects_list = (JSONArray) response_jason.get(todos);
            int todos_list_size = projects_list.size();
            assertTrue(todos_list_size >= 1);
        }
        catch(Exception ParseException){
            ParseException.printStackTrace();
        }
    }

    private JSONObject send_request(String toDoEndPoint, int status) throws IOException, ParseException {
        HttpUriRequest request = new HttpGet(  baseUrl+ toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(status, httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
        return response_jason;
    }

    private JSONObject send_post_request(String toDoEndPoint,int status, StringEntity userEntity) throws IOException, ParseException {
        HttpPost request = new HttpPost(  baseUrl+ toDoEndPoint);
        request.addHeader("content-type", "application/json");
        request.setEntity(userEntity);
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(status, httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
        return response_jason;
    }

    private JSONObject send_delete_request(String toDoEndPoint,int status) throws IOException, ParseException {
        HttpUriRequest request = new HttpDelete(  baseUrl+ toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(status, httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
        return response_jason;
    }

    String expected_id = "1";
    String title_value = "Test_task_001";
    String result_id = null;
    String description_value = "This is description";
    StringEntity userEntity;

    // ***********************************************
    // AddTask Normal Flow
    // ***********************************************

    @When("I add a task to the todo list with a specific title")
    public void iAddATaskToTheTodoListWithASpecificTitle() {

        try {
            JSONObject json = new JSONObject();
            json.put(title, title_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason = send_post_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 201, userEntity);
            assertEquals(title_value, (String) (response_jason.get(title)));
            result_id = (String) response_jason.get("id");
        } catch (Exception ParseException) {
            System.out.println("Failure at AddTestS1");
        }
    }

    @Then("I verify that there is a task in the todo list associated with the project with the title")
    public void iVerifyThatThereIsATaskInTheTodoListAssociatedWithTheProjectWithTheTitle() {
        try {
            JSONObject response_jason = send_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 200);
            JSONArray projects_list = (JSONArray) response_jason.get(project);
            for(int i=0;i < projects_list.size();i++){
                if(((JSONObject)projects_list.get(i)).get(title).equals(title_value)){
                    assertEquals(title_value, ((JSONObject)projects_list.get(i)).get(title));
                }
            }
        } catch (Exception ParseException) {
            System.out.println("Failure at AddTestS1");
        }
    }


    // ***********************************************
    // AddTask Alternate Flow
    // ***********************************************
    @When("I add a task to the todo list with a specific title and a description")
    public void iAddATaskToTheTodoListWithASpecificTitleAndADescription() {
        try {
            JSONObject json = new JSONObject();
            json.put(title, title_value);
            json.put(description,description_value );
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason = send_post_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 201, userEntity);
            assertEquals(title_value, (String) (response_jason.get(title)));
            result_id = (String) response_jason.get("id");
        } catch (Exception ParseException) {
            System.out.println("Failure at AddTestS2");
        }
    }

    @Then("I verify that there is a task in the todo list associated with the project with the title and the description")
    public void iVerifyThatThereIsATaskInTheTodoListAssociatedWithTheProjectWithTheTitleAndTheDescription() {
        try {
            JSONObject response_jason = send_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 200);
            JSONArray projects_list = (JSONArray) response_jason.get(project);
            for(int i=0;i < projects_list.size();i++){
                if(((JSONObject)projects_list.get(i)).get(title).equals(title_value)){
                    assertEquals(title_value, ((JSONObject)projects_list.get(i)).get(title));
                    assertEquals(description_value, ((JSONObject)projects_list.get(i)).get(description));
                }
            }
        } catch (Exception ParseException) {
            System.out.println("Failure at AddTestS2");
        }
    }

    // ***********************************************
    // AddTask Error Flow
    // ***********************************************
    @Given("I deleted the todo list")
    public void iDeletedTheTodoList() {
        try {
            JSONObject response_jason = send_delete_request(toDoEndIDPoint + expected_id, 200);
        }
        catch(Exception e){
            System.out.println("Failure at AddTestS3");
        }
    }


    @When("I add a task to the todo list")
    public void iAddATaskToTheTodoList() {
        try{
            JSONObject json = new JSONObject();
            json.put(title, title_value);
            userEntity = new StringEntity(json.toString());
        }
        catch(Exception e){
            System.out.println("Failure at AddTestS3");
        }

    }

    @Then("I shall be notified that the todo list doesn't exist")
    public void iShallBeNotifiedThatTheTodoListDoesnTExist() {
        try {
            JSONObject response_jason = send_post_request(toDoEndIDPoint +expected_id+tasksOfEndPoint, 202, userEntity);
            assertEquals(title_value, (String) (response_jason.get(title)));
            result_id = (String) response_jason.get("id");
        } catch (Exception ParseException) {
            System.out.println("Failure at AddTestS3");
        }
    }
}

