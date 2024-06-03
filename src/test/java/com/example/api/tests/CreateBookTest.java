package com.example.api.tests;

import com.example.api.models.request.CreateBookRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Library Service")
@Story("Создание новой книги")
public class CreateBookTest extends BaseTest {

    @Test
    @DisplayName("Сохранение новой книги")
    @Description("Тест для проверки успешного сохранения новой книги")
    public void testCreateBook() {
        CreateBookRequest request = new CreateBookRequest();
        request.setBookTitle("Детство");
        CreateBookRequest.Author author = new CreateBookRequest.Author();
        author.setId(653L);
        request.setAuthor(author);

        givenAuth()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/books/save")
                .then()
                .statusCode(200)
                .body("bookId", equalTo(4734));
    }
}