package gustavoneery.libraryapi.service;

import gustavoneery.libraryapi.dto.RequestAuthorDto;
import gustavoneery.libraryapi.exceptions.OperationNotPermittedException;
import gustavoneery.libraryapi.model.Author;
import gustavoneery.libraryapi.repository.AuthorRepository;
import gustavoneery.libraryapi.repository.BookRepository;
import gustavoneery.libraryapi.validator.AuthorValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private AuthorRepository repository;
    private AuthorValidator validator;
    private BookRepository bookRepository;

    public AuthorService(AuthorRepository repository, AuthorValidator validator, BookRepository bookRepository) {
        this.repository = repository;
        this.validator = validator;
        this.bookRepository = bookRepository;
    }

    public void save(Author author) {
        validator.validate(author);
        repository.save(author);
    }

    public RequestAuthorDto findById(UUID id) throws ClassNotFoundException {
        Optional<Author> optionalAuthor = repository.findById(id);

        if(optionalAuthor.isEmpty()) {
            throw new ClassNotFoundException("Author not found");
        }

        Author author = optionalAuthor.get();
        return new RequestAuthorDto(author.getId(), author.getName(), author.getBornDate(), author.getNationality(), author.getUserId());
    }

    public Page<Author> findAuthors(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
       return repository.findAll(pageable);
    }

    public void deleteById(UUID id) throws ClassNotFoundException {
        Optional<Author> optionalAuthor = repository.findById(id);

        if(optionalAuthor.isEmpty()) {
            throw new ClassNotFoundException("Author not found");
        }

        if(hasBook(optionalAuthor.get())) {
            throw new OperationNotPermittedException("Not is permitted delete Author when he has book registered.");
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

        validator.validate(author);
        repository.save(author);
    }

    public boolean hasBook(Author author) {
        return bookRepository.existsByAuthor(author);
    }
}
