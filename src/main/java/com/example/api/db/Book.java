package com.example.api.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class Book {
    private long id;
    private String bookTitle;
    private long authorId;
    private Date updated;
}
