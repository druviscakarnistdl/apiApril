package apiApril.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrelloSteps {
    @Given("The board exists and contains correct information")
    public void getBoardDataAndCheckInfo(){
        System.out.println("STEP 1");
    }

    @When("I change board title to {string}")
    public void changeBoardTitle(String title){
        System.out.println("STEP 2 " + title);
    }

    @And("I check that the board name is updated to {string}")
    public void checkBoardNameIsUpdated(String title) {
        System.out.println("STEP 3 " + title);
    }

    @Then("I add a list with a name {string} to the board")
    public void iAddAListWithANameToTheBoard(String listName) {
        System.out.println("STEP 4 " + listName);
    }
}
