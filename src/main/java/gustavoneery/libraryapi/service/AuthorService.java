package gustavoneery.libraryapi.service;

import gustavoneery.libraryapi.dto.AuthorResponseDto;
import gustavoneery.libraryapi.dto.RequestAuthorDto;
import gustavoneery.libraryapi.model.Author;
import gustavoneery.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public void save(RequestAuthorDto requestAuthorDto) {
        Author author = new Author();
        author.setName(requestAuthorDto.name());
        author.setBornDate(requestAuthorDto.bornDate());
        author.setNationality(requestAuthorDto.nationality());

        repository.save(author);
    }
}
