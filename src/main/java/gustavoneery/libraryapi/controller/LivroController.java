package gustavoneery.libraryapi.controller;

import gustavoneery.libraryapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class LivroController {

    private final BookService bookService;
}
