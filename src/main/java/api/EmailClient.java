package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

public class EmailClient {
    private static final String BASE_URL = "https://www.1secmail.com/";

    @Step("Получение списка писем")
    public static Email[] getAll(String login) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .param("action", "getMessages")
                .param("login", login)
                .param("domain", "1secmail.com")
                .get("api/v1/")
                .as(Email[].class);
    }

    @Step("Получение содержимого конкертного письма")
    public static ValidatableResponse get(String login, int id) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .param("action", "readMessage")
                .param("login", login)
                .param("domain", "1secmail.com")
                .param("id", id)
                .get("api/v1/")
                .then();
    }
}
