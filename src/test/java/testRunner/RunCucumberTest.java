package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"junit"}, features = "src/test/resources/features/DeleteTask.feature")

public class RunCucumberTest {

}
