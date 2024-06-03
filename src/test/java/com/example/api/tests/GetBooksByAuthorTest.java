package com.example.api.tests;

import com.example.api.models.response.GetBooksByAuthorResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Epic("Library Service")
@Story("Получение книг по автору")
public class GetBooksByAuthorTest extends BaseTest {

    @Test
    @DisplayName("Тест получения книг по автору (JSON)")
    @Description("Проверка успешного получения книг по автору в формате JSON")
    public void testGetBooksByAuthor() {
        given()
                .header("Authorization", "Bearer " + getAuthToken())
                .contentType(ContentType.JSON)
                .get("/authors/653/books")
                .then()
                .statusCode(200)
                .body("books", hasSize(greaterThan(0)))
                .body("books[0].bookTitle", equalTo("Детство"))
                .body("books[0].author.firstName", equalTo("Лев"))
                .body("books[0].author.familyName", equalTo("Толстой"));
    }

    @Test
    @DisplayName("Тест получения книг по автору (XML)")
    @Description("Проверка успешного получения книг по автору в формате XML")
    public void testGetBooksByAuthorXml() throws JAXBException {
        String xmlResponse = given()
                .header("Authorization", "Bearer " + getAuthToken())
                .contentType(ContentType.XML)
                .accept(ContentType.XML)
                .get("/authors/653/books")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        JAXBContext jaxbContext = JAXBContext.newInstance(GetBooksByAuthorResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        GetBooksByAuthorResponse booksResponse = (GetBooksByAuthorResponse) unmarshaller.unmarshal(new StringReader(xmlResponse));

        assertNotNull(booksResponse);
        assertNotNull(booksResponse.getBooks());
        assertThat(booksResponse.getBooks(), hasSize(greaterThan(0)));

        GetBooksByAuthorResponse.BookDetails firstBook = booksResponse.getBooks().get(0);
        assertThat(firstBook.getBookTitle(), equalTo("Детство"));
        assertThat(firstBook.getAuthor().getFirstName(), equalTo("Лев"));
        assertThat(firstBook.getAuthor().getFamilyName(), equalTo("Толстой"));
    }

    @NotNull
    private String getAuthToken() {
        RestAssured.baseURI = "http://localhost:8080";

        String login = "example_user";
        String password = "!QAZxsw2";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"login\": \"" + login + "\", \"password\": \"" + password + "\"}")
                .post("/auth/login");

        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response headers: " + response.getHeaders());
        System.out.println("Response body: " + response.getBody().asString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to authenticate: " + response.statusLine());
        }

        String token = response.jsonPath().getString("token");
        System.out.println("Extracted token: " + token);

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Failed to retrieve token from response");
        }

        return token;
    }
}