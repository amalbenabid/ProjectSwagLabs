package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (features = "src/test/java/Features", glue = {"StepDefenition"},
/*tags = (
        "@logout and @suppression"), */
        plugin = {"pretty", "html:target\\Rapport\\Rapport.html"})
public class TestRunner {

}
