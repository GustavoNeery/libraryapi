package gustavoneery.libraryapi.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDto(UUID id, String name, LocalDate bornDate, String nationality) {
}
