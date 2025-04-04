package gustavoneery.libraryapi.service;

import gustavoneery.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private BookRepository bookRepository;
}
