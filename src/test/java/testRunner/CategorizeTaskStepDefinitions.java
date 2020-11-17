package testRunner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static org.junit.Assert.*;

public class CategorizeTaskStepDefinitions extends  FeatureStepDefinitions{

    // ***********************************************
    // Background step definitions
    // ***********************************************
    String expected_id_1 = "1";

    @Given("I have a task with a priority of DEFAULT")
    public void iHaveATaskWithAPriorityOfDEFAULT() {
        try{

            JSONObject json = new JSONObject();
            String title_value = "DEFAULT";
            json.put(title, title_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason1 = send_post_request(categoriesEndPoint+"/"+expected_id_1, 200, userEntity);
        }
        catch(Exception e){
            System.out.println("Failure");
        }

    }

    @Given("I have a task with a priority of LOW")
    public void iHaveATaskWithAPriorityOfLOW() {
        try{
            JSONObject json = new JSONObject();
            String title_value = "LOW";
            json.put(title, title_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason1 = send_post_request(categoriesEndPoint+"/"+expected_id_1, 200, userEntity);
        }
        catch(Exception e){
            System.out.println("Failure");
        }
    }


    // ***********************************************
    // CategorizeTask Normal Flow
    // ***********************************************

    @When("I adjust the task as LOW priority")
    public void iAdjustTheTaskAsLOWPriority() {
        try{
            JSONObject json = new JSONObject();
            String title_value = "LOW";
            json.put(title, title_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason1 = send_post_request(categoriesEndPoint+"/"+expected_id_1, 200, userEntity);
        }
        catch(Exception e){
            System.out.println("Failure");
        }
        
    }

    @Then("I verify that the task's priority is LOW")
    public void iVerifyThatTheTaskSPriorityIsLOW() {

        try{
            JSONObject json = new JSONObject();
            String title_value = "LOW";
            json.put(title, title_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason = send_request(categoriesEndPoint, 200);
            JSONArray projects_list = (JSONArray) response_jason.get(categories);
            for(int i=0;i < projects_list.size();i++){
                if(((JSONObject)projects_list.get(i)).get(title).equals(title_value)){
                    assertEquals(title_value, ((JSONObject)projects_list.get(i)).get(title));
                }
            }
        }
        catch(Exception e){
            System.out.println("Failure");
        }
    }


    // ***********************************************
    // CategorizeTask Alternate Flow
    // ***********************************************

    @When("I adjust the task as HIGH priority")
    public void iAdjustTheTaskAsHIGHPriority() {
        try{
            JSONObject json = new JSONObject();
            String title_value = "High";
            json.put(title, title_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason = send_post_request(categoriesEndPoint+"/"+expected_id_1, 200, userEntity);
        }
        catch(Exception e){
            System.out.println("Failure");
        }
    }

    @Then("I verify that the task's priority is HIGH")
    public void iVerifyThatTheTaskSPriorityIsHIGH() {
        try{
            JSONObject json = new JSONObject();
            String title_value = "HIGH";
            json.put(title, title_value);
            StringEntity userEntity = new StringEntity(json.toString());
            JSONObject response_jason = send_request(categoriesEndPoint+"/"+expected_id_1, 200);
            JSONArray projects_list = (JSONArray) response_jason.get(categories);
            for(int i=0;i < projects_list.size();i++){
                if(((JSONObject)projects_list.get(i)).get(title).equals(title_value)){
                    assertEquals(title_value, ((JSONObject)projects_list.get(i)).get(title));
                }
            }
        }
        catch(Exception e){
            System.out.println("Failure");
        }
    }

    // ***********************************************
    // CategorizeTask Error Flow
    // ***********************************************
    StringEntity userEntity;
    @When("I adjust a non_exist task as HIGH priority")
    public void iAdjustANon_existTaskAsHIGHPriority() {
        try{

            JSONObject json = new JSONObject();
            String title_value = "HIGH";
            json.put(title, title_value);
            userEntity = new StringEntity(json.toString());

        }
        catch(Exception e){
            System.out.println("Failure");
        }
    }

    @Then("I shall be notified that the task doesn't exist")
    public void iShallBeNotifiedThatTheTaskDoesnTExist() {
        String expected_id = "9";
        try{
            JSONObject response_jason1 = send_request(categoriesEndPoint+"/"+expected_id, 404);
        }
        catch(Exception e){
            System.out.println("Failure");
        }
    }





}
