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
    private static final String UUID_STRING = "2d7266ee-3ed0-43a3-b1f8-11fb6b2a94b2";

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
        UUID uuidConverted = convertFromStringToUUID(UUID_STRING);
        Optional<Author> author = findAuthorById(uuidConverted);
        if(author.isPresent()) {
            repository.deleteById(uuidConverted);
        }
    }

    @Test
    public void testUpdate() {
        UUID uuidConverted = convertFromStringToUUID(UUID_STRING);
        Optional<Author> authorOptional = findAuthorById(uuidConverted);
        if(authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName("Elisangela");
            repository.save(author);
        }
    }

    private Optional<Author> findAuthorById(UUID id){
        return repository.findById(id);
    }

    private UUID convertFromStringToUUID(String UUID_STRING) {
        return UUID.fromString(UUID_STRING);
    }

}
