package MyRunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = { "src/main/java/Features/" },
	glue={"stepDefinitions"},
	tags = {"@Login"}, 
	snippets = SnippetType.CAMELCASE)

public class TestRunner {
	@AfterClass
	public static void tearDown() {
	}
}
