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


public class FeatureStepDefinitions {
    public static final String baseUrl = "http://localhost:4567/";
    public static final String toDoEndPoint = "todos";
    public static final String toDoEndIDPoint = "todos/";
    public static final String tasksOfEndPoint = "/tasksof";
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

    JSONObject send_request(String toDoEndPoint, int status) throws IOException, ParseException {
        HttpUriRequest request = new HttpGet(  baseUrl+ toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(status, httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
        return response_jason;
    }

    JSONObject send_post_request(String toDoEndPoint,int status, StringEntity userEntity) throws IOException, ParseException {
        HttpPost request = new HttpPost(  baseUrl+ toDoEndPoint);
        request.addHeader("content-type", "application/json");
        request.setEntity(userEntity);
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(status, httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
        return response_jason;
    }

    JSONObject send_delete_request(String toDoEndPoint,int status) throws IOException, ParseException {
        HttpUriRequest request = new HttpDelete(  baseUrl+ toDoEndPoint);
        HttpResponse httpResponse = httpClient.execute(request);
        if(status!=0) {
            assertEquals(status, httpResponse.getStatusLine().getStatusCode());
        }
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject response_jason = (JSONObject) jsonParser.parse(responseBody);
        return response_jason;
    }


}
