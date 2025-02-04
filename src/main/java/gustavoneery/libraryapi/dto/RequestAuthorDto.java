package gustavoneery.libraryapi.dto;

import java.time.LocalDate;

public record RequestAuthorDto(String name, LocalDate bornDate, String nationality) {
}
