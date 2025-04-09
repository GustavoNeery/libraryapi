package gustavoneery.libraryapi.repository;

import gustavoneery.libraryapi.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<Author> findByNameAndNationality(String name, String nationality);
    Optional<Author> findByNameAndNationalityAndBornDate(String name, String nationality, LocalDate bornDate);

    Page<Author> findAll(Pageable pageable);
}
