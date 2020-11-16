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

public class AddTestStepDefinitions {


    public static final String baseUrl = "http://localhost:4567/";
    public static final String projectEndPoint = "projects";
    public static final String tasksEndIDPoint = "/tasks";
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


    // ***********************************************
    // AddTask Normal Flow
    // ***********************************************

    @When("I add a task to the todo list with a specific title")
    public void iAddATaskToTheTodoListWithASpecificTitle() {

    }

    @Then("I verify that there is a task in the todo list associated with the project with the title")
    public void iVerifyThatThereIsATaskInTheTodoListAssociatedWithTheProjectWithTheTitle() {
    }

    @When("I add a task to the todo list with a specific title and a description")
    public void iAddATaskToTheTodoListWithASpecificTitleAndADescription() {

    }

    @Then("I verify that there is a task in the todo list associated with the project with the title and the description")
    public void iVerifyThatThereIsATaskInTheTodoListAssociatedWithTheProjectWithTheTitleAndTheDescription() {

    }

    @Given("I deleted the project")
    public void iDeletedTheProject() {

    }

    @When("I add a task to the todo list")
    public void iAddATaskToTheTodoList() {

    }

    @Then("I shall be notified that the project doesn't exist")
    public void iShallBeNotifiedThatTheProjectDoesnTExist() {

    }

    @Given("I deleted the todo list")
    public void iDeletedTheTodoList() {

    }

    @Then("I shall be notified that the todo list doesn't exist")
    public void iShallBeNotifiedThatTheTodoListDoesnTExist() {
    }
}

