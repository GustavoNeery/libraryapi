package gustavoneery.libraryapi.controller;

import gustavoneery.libraryapi.dto.RequestAuthorDto;
import gustavoneery.libraryapi.model.Author;
import gustavoneery.libraryapi.service.AuthorService;
import jakarta.persistence.EntityListeners;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody RequestAuthorDto requestAuthorDto) {
        Author author = requestAuthorDto.mapToAuthor();
        authorService.save(author);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestAuthorDto> findById(@PathVariable("id") String id) throws ClassNotFoundException {
        var authorId = UUID.fromString(id);
        RequestAuthorDto dto = authorService.findById(authorId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) throws ClassNotFoundException {
        var authorId = UUID.fromString(id);
        authorService.deleteById(authorId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<RequestAuthorDto>> findByNameAndNationality(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "nationality", required = false) String nationality){
        List<RequestAuthorDto> authors = authorService.findByNameAndNationality(name, nationality);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
