package gustavoneery.libraryapi.exceptions.common;

import gustavoneery.libraryapi.dto.FieldError;
import gustavoneery.libraryapi.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getFieldErrors()
                .stream()
                .map(f -> new FieldError(f.getField(), f.getDefaultMessage()))
                .toList();
        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Error", errors);
    }
}

