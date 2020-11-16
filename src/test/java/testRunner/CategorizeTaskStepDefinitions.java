package testRunner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertTrue;

public class CategorizeTaskStepDefinitions extends  FeatureStepDefinitions{

    // ***********************************************
    // Background step definitions
    // ***********************************************

    @Given("I have a task with a priority of DEFAULT")
    public void iHaveATaskWithAPriorityOfDEFAULT() {


    }

    @Given("I have a task with a priority of LOW")
    public void iHaveATaskWithAPriorityOfLOW() {

    }


    // ***********************************************
    // CategorizeTask Normal Flow
    // ***********************************************

    @When("I adjust the task as LOW priority")
    public void iAdjustTheTaskAsLOWPriority() {
        
        
    }

    @Then("I verify that the task's priority is LOW")
    public void iVerifyThatTheTaskSPriorityIsLOW() {
    }


    // ***********************************************
    // CategorizeTask Alternate Flow
    // ***********************************************

    @When("I adjust the task as HIGH priority")
    public void iAdjustTheTaskAsHIGHPriority() {
    }

    // ***********************************************
    // CategorizeTask Error Flow
    // ***********************************************


    @Then("I shall be notified that the task doesn't exist")
    public void iShallBeNotifiedThatTheTaskDoesnTExist() {
        
    }

    @Then("I verify that the task's priority is HIGH")
    public void iVerifyThatTheTaskSPriorityIsHIGH() {
    }

    @When("I adjust a non_exist task as HIGH priority")
    public void iAdjustANon_existTaskAsHIGHPriority() {
    }
}
