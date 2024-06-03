package com.example.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CreateBookResponse {
    private int bookId;
    private Integer errorCode;
    private String errorMessage;
    private String errorDetails;
}
