package apiApril.stepdefinitions;

import apiApril.helpers.TestCaseContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static apiApril.clients.TrelloClient.deleteList;
import static apiApril.clients.TrelloClient.updateBoardInfo;
import static apiApril.constants.ProjectConstants.BOARD_NAME;

public class Hooks {
    @Before
    public void before(Scenario scenario) {
        TestCaseContext.init();
        TestCaseContext.setScenario(scenario);
    }

    @After
    public void after() {
        updateBoardInfo(TestCaseContext.getBoard().getId(), BOARD_NAME);
        deleteList(TestCaseContext.getList().getId());
    }
}
