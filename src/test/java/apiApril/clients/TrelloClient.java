package apiApril.clients;

import static apiApril.constants.ProjectConstants.API_KEY;
import static apiApril.constants.ProjectConstants.API_TOKEN;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TrelloClient {

    private static RequestSpecification trelloSpec() {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN);

    }
    public static Response getBoardInfo(String boardId) {
        return RestAssured
                .given(trelloSpec())
                .when()
                .get("https://api.trello.com/1/boards/" + boardId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response updateBoardInfo(String boardId, String newName) {
        return RestAssured
                .given(trelloSpec())
                .queryParam("name", newName)
                .when()
                .put("https://api.trello.com/1/boards/" + boardId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createList(String boardId, String listName) {
        return RestAssured
                .given(trelloSpec())
                .queryParam("idBoard", boardId)
                .queryParam("name", listName)
                .when()
                .post("https://api.trello.com/1/lists/")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response deleteList(String listId) {
        return RestAssured
                .given(trelloSpec())
                .queryParam("value", true)
                .when()
                .put("https://api.trello.com/1/lists/" + listId + "/closed")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}
