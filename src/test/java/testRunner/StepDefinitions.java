package testRunner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
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
import org.apache.http.HttpResponse;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class StepDefinitions extends FeatureStepDefinitions {

    private static Process process;
    // ***********************************************
    // Background step definitions
    // ***********************************************

    @Before
    public static void initial_server () throws Exception {
        ArrayList<String> command = new ArrayList<String>();
        //System.getProperty("java.home") + "/bin/java"
        command.add("java"); // quick and dirty for unix
        command.add("-jar");
        command.add("/Users/hehuimincheng/ECSE429/runTodoManagerRestAPI-1.5.5.jar");

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        process = builder.inheritIO().start();
        Thread.sleep(500);
    }

    @Given("I have connected to the TodoManager Server")
    public void the_system_is_running() throws Exception {
        try{
            URL url = new URL(baseUrl);
            connection= (HttpURLConnection) url.openConnection();
            connection.connect();
            assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
        }
        catch(Exception e){
            System.out.println("Error in connection");
            e.printStackTrace();
            throw new Exception();
        }

    }

    @And("I have a course \\(project)")
    public void i_have_a_course_project() throws ClientProtocolException, IOException {
        try{
            JSONObject response_jason = send_request(projectEndPoint, 200);
            JSONArray projects_list = (JSONArray) response_jason.get(project);

            int todos_list_size = projects_list.size();
            assertTrue(todos_list_size >= 1);

        }
        catch(Exception ParseException){
            System.out.println("Failure1");
        }
    }


    @After
    public static void afterClass() throws Exception{
        process.destroy();
        Thread.sleep(500);
    }


}

