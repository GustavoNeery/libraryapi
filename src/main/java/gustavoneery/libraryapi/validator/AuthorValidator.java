package gustavoneery.libraryapi.validator;

import gustavoneery.libraryapi.exceptions.RegistryDuplicatedException;
import gustavoneery.libraryapi.model.Author;
import gustavoneery.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    public void validate(Author author) {
        Optional<Author> authorOptional = repository.findByNameAndNationalityAndBornDate(author.getName(), author.getNationality(), author.getBornDate());

        if(isAuthorExists(authorOptional)) {
            throw new RegistryDuplicatedException("Already exists that author");
        }

        if(!isSameAuthorId(author, authorOptional.get())) {
            throw new RegistryDuplicatedException("An author with the same name," +
                    "nationality, and birthdate but a different ID already exists.");
        }
    }

    private boolean isAuthorExists(Optional<Author> authorOptional) {
        return authorOptional.isPresent();
    }

    private boolean isSameAuthorId(Author author, Author authorToUpdate) {
        return author.getId().equals(authorToUpdate.getId());
    }

}
