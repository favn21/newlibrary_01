package com.example.api.models.response;

import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@XmlRootElement(name = "authors_books")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetBooksByAuthorResponse {

    @XmlElement(name = "books")
    private List<BookDetails> books;

    public void setBooks(List<BookDetails> books) {
        this.books = books;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BookDetails {

        @XmlElement(name = "book_title")
        private String bookTitle;

        @XmlElement(name = "updated")
        private String updated;

        @XmlElement(name = "author")
        private AuthorDetails author;

        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public AuthorDetails getAuthor() {
            return author;
        }

        public void setAuthor(AuthorDetails author) {
            this.author = author;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        public static class AuthorDetails {

            @XmlElement(name = "first_name")
            private String firstName;

            @XmlElement(name = "family_name")
            private String familyName;

            @XmlElement(name = "second_name")
            private String secondName;

            @XmlElement(name = "birth_date")
            private String birthDate;

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getFamilyName() {
                return familyName;
            }

            public void setFamilyName(String familyName) {
                this.familyName = familyName;
            }

            public String getSecondName() {
                return secondName;
            }

            public void setSecondName(String secondName) {
                this.secondName = secondName;
            }

            public String getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(String birthDate) {
                this.birthDate = birthDate;
            }
        }
    }
}