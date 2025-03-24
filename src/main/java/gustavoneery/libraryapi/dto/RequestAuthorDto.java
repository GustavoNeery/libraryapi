package gustavoneery.libraryapi.dto;

import gustavoneery.libraryapi.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record RequestAuthorDto(UUID id, String name, LocalDate bornDate, String nationality, UUID userId) {

    public Author mapToAuthor(){
        Author author = new Author();
        author.setName(this.name());
        author.setBornDate(this.bornDate());
        author.setNationality(this.nationality());
        author.setUserId(UUID.randomUUID());
        return author;
    }
}
