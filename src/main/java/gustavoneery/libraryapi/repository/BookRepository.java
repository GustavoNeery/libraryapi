package gustavoneery.libraryapi.repository;

import gustavoneery.libraryapi.model.Author;
import gustavoneery.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    boolean existsByAuthor(Author author);
}