package gustavoneery.libraryapi.controller;

import gustavoneery.libraryapi.dto.RequestAuthorDto;
import gustavoneery.libraryapi.dto.ResponseError;
import gustavoneery.libraryapi.exceptions.OperationNotPermittedException;
import gustavoneery.libraryapi.exceptions.RegistryDuplicatedException;
import gustavoneery.libraryapi.model.Author;
import gustavoneery.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Pageable;


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
    public ResponseEntity save(@Valid @RequestBody RequestAuthorDto requestAuthorDto) {
        try {
            Author author = requestAuthorDto.mapToAuthor();
            authorService.save(author);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(author.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RegistryDuplicatedException e) {
            var errorDto = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(errorDto.status()).body(errorDto);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestAuthorDto> findById(@PathVariable("id") String id) throws ClassNotFoundException {
        var authorId = UUID.fromString(id);
        RequestAuthorDto dto = authorService.findById(authorId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Author>> findAll(@RequestParam(
                                                        value = "page-number",
                                                        defaultValue = "0") int pageNumber,
                                                @RequestParam(
                                                        value = "page-size",
                                                        defaultValue = "10") int pageSize) {
        Page<Author> authors = authorService.findAuthors(pageNumber, pageSize);

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) throws ClassNotFoundException {
        try {
            var authorId = UUID.fromString(id);
            authorService.deleteById(authorId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OperationNotPermittedException e) {
            var errorDto = ResponseError.patternResponse(e.getMessage());
            return ResponseEntity.status(errorDto.status()).body(errorDto);
        }
    }

    @GetMapping
    public ResponseEntity<List<RequestAuthorDto>> findByNameAndNationality(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "nationality", required = false) String nationality){
        List<RequestAuthorDto> authors = authorService.findByNameAndNationality(name, nationality);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody RequestAuthorDto requestAuthorDto) {
        try {
            authorService.update(id, requestAuthorDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RegistryDuplicatedException e) {
            var errorDto = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(errorDto.status()).body(errorDto);
        }
    }
}
