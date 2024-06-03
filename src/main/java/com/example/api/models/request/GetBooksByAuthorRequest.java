package com.example.api.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@XmlRootElement(name = "GetBooksByAuthorRequest")
public class GetBooksByAuthorRequest {
    @XmlElement(name = "authorId")
    private Long authorId;
}
