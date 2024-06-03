package com.example.api.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CreateBookRequest {
    private String bookTitle;
    private Author author;

    @Data
    public static class Author {
        private Long id;
    }
}
