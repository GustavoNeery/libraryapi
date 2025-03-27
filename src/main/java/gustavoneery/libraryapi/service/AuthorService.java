package gustavoneery.libraryapi.service;

import gustavoneery.libraryapi.dto.RequestAuthorDto;
import gustavoneery.libraryapi.model.Author;
import gustavoneery.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void deleteById(UUID id) throws ClassNotFoundException {
        Optional<Author> optionalAuthor = repository.findById(id);

        if(optionalAuthor.isEmpty()) {
            throw new ClassNotFoundException("Author not found");
        }

        repository.deleteById(id);
    }

    public List<RequestAuthorDto> findByNameAndNationality(String name, String nationality) {
        List<Author> authors = repository.findByNameAndNationality(name, nationality);

        return authors.stream().map(author -> new RequestAuthorDto(author.getId(), author.getName(), author.getBornDate(),
                author.getNationality(), author.getUserId())).toList();
    }

    public void update(UUID id, RequestAuthorDto dto) {
        Optional<Author> authorOptional = repository.findById(id);

        if(authorOptional.isEmpty()) {
            throw new IllegalArgumentException("Author not found");
        }

        Author author = authorOptional.get();
        author.setName(dto.name());
        author.setBornDate(dto.bornDate());
        author.setNationality(dto.nationality());

        repository.save(author);
    }
}
