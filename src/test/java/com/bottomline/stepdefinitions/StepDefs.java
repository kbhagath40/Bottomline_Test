package com.bottomline.stepdefinitions;

import com.bottomline.util.WebConnector;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	
WebConnector cucumber = WebConnector.getInstance();
	
	@Given("^I open \"([^\"]*)\"$")
	public void I_Open_Browser(String browser) {
		cucumber.OpenBrowser(browser);
	}

	@When("^I navigate \"([^\"]*)\"$")
	public void I_navigate(String url) {
		cucumber.navigate(url);
	}

	@And("^I enter \"([^\"]*)\" as \"([^\"]*)\"$")
	public void I_enter(String prop, String text) {
		cucumber.type(prop, text);
	}

	@When("^I click on \"([^\"]*)\"$")
	public void I_click_on(String property) throws InterruptedException {
		cucumber.click(property);
		Thread.sleep(3000);
	}
	
	@And("^I click on \"([^\"]*)\" select as \"([^\"])\"$")
	public void select_As(String prop, String text){
		cucumber.selectValue(prop, text);
	}
	
	@And("^I type \"([^\"]*)\" as \"([^\"]*)\"$")
	public void I_type(String prop, String text){
		cucumber.clearText("first_name");
		cucumber.type(prop, text);
	}
	@And("^I refresh the page$")
	public void refresh_Page(){
		cucumber.refresh();
		cucumber.acceptAlert();
		
	}

	@Then("^Verify the \"([^\"]*)\" is equal to \"([^\"]*)\"$")
	public void check(String actualResult, String expectedResult) throws InterruptedException {
		//Thread.sleep(3000);
		cucumber.explicit_Wait("first_name");
		cucumber.verify(actualResult, expectedResult);
	
	}
	
	@And("^I close the browser$")
	public void close_the_browser() {
		cucumber.closeBrowser();

	}

}
