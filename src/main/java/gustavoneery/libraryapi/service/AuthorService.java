package gustavoneery.libraryapi.service;

import gustavoneery.libraryapi.dto.RequestAuthorDto;
import gustavoneery.libraryapi.model.Author;
import gustavoneery.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public void save(Author author) {
        repository.save(author);
    }

    public RequestAuthorDto findById(UUID id) throws ClassNotFoundException {
        Optional<Author> optionalAuthor = repository.findById(id);

        if(optionalAuthor.isEmpty()) {
            throw new ClassNotFoundException("Author not found");
        }

        Author author = optionalAuthor.get();
        RequestAuthorDto requestAuthorDto = new RequestAuthorDto(author.getId(), author.getName(), author.getBornDate(), author.getNationality(), author.getUserId());
        return requestAuthorDto;
    }
}
