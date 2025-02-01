package gustavoneery.libraryapi.repository;

import gustavoneery.libraryapi.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {
    private static final String UUID_STRING = "4e4310a1-5d5e-4f29-b9cf-9d62fc10d69b";

    @Autowired
    private AuthorRepository repository;

    @Test
    public void testSave(){
        Author author = new Author();
        author.setName("Luiz");
        author.setBornDate(LocalDate.of(1999, 11, 6));
        author.setNationality("Brasileiro");

        Author savedAuthor = repository.save(author);
        System.out.println("author created: " + savedAuthor);
    }

    @Test
    public void testDeleteById(){
        UUID uuidConverted = UUID.fromString(UUID_STRING);
        Optional<Author> author = repository.findById(uuidConverted);
        if(author.isPresent()) {
            repository.deleteById(uuidConverted);
        }
    }
}
