package com.example.api.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class Author {
    private long id;
    private String firstName;
    private String familyName;
    private String secondName;
    private Date birthDate;
}
