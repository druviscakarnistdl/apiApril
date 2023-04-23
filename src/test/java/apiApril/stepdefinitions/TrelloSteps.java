package apiApril.stepdefinitions;

import apiApril.domain.Board;
import apiApril.domain.List;
import apiApril.helpers.TestCaseContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import static apiApril.clients.TrelloClient.*;
import static apiApril.constants.ProjectConstants.BOARD_ID;
import static apiApril.constants.ProjectConstants.BOARD_NAME;

public class TrelloSteps {
    @Given("The board exists and contains correct information")
    public void getBoardDataAndCheckInfo(){
        Response response = getBoardInfo(BOARD_ID);
        Board board = response.as(Board.class);

        Assertions.assertThat(board.getId())
                .as("We assert that board ID is correct!")
                .isEqualTo(BOARD_ID);
        Assertions.assertThat(board.getName())
                .as("We assert that board name is correct!")
                .isEqualTo(BOARD_NAME);
    }

    @When("I change board title to {string}")
    public void changeBoardTitle(String title){
        Response response = updateBoardInfo(BOARD_ID, title);
        Board board = response.as(Board.class);
        TestCaseContext.setBoard(board);
    }

    @And("I check that the board name is updated to {string}")
    public void checkBoardNameIsUpdated(String title) {
        Assertions.assertThat(TestCaseContext.getBoard().getName())
                .as("We check that board name is updated to " + title)
                .isEqualTo(title);
    }

    @Then("I add a list with a name {string} to the board")
    public void iAddAListWithANameToTheBoard(String listName) {
        Response response = createList(TestCaseContext.getBoard().getId(), listName);
        List list = response.as(List.class);
        TestCaseContext.setList(list);

        Assertions.assertThat(TestCaseContext.getList().getName())
                .as("We check that list is created " + listName)
                .isEqualTo(listName);

        TestCaseContext.getScenario().log(TestCaseContext.getList().getId());
    }
}
