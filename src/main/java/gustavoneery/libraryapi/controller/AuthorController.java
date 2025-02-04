package gustavoneery.libraryapi.controller;

import gustavoneery.libraryapi.dto.RequestAuthorDto;
import gustavoneery.libraryapi.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody RequestAuthorDto requestAuthorDto) {
        authorService.save(requestAuthorDto);
        return new ResponseEntity("author created with success!"+ requestAuthorDto, HttpStatus.CREATED);
    }
}
