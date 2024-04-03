package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserAndClient {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    @Step("Создание нового пользователя")
    public static ValidatableResponse create(User user) {

        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register")
                .then();
    }

    @Step("Удаление пользователя")
    public static ValidatableResponse delete(String token) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .delete("/api/auth/user")
                .then();
    }

    @Step("Авторизация пользователя")
    public static ValidatableResponse login(User user) {

        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/login")
                .then();
    }
}