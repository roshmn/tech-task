package com.book.trendyol.exception;

import com.book.trendyol.exception.model.BookAlreadyExistsException;
import com.book.trendyol.exception.model.BookIdNotWritableException;
import com.book.trendyol.exception.model.BookNotFoundException;
import com.book.trendyol.exception.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBookNotFound(BookNotFoundException e) {
        String m = String.format("Book with 'id' = '%d' not exists!", e.getBookId());
        return new ResponseEntity<>(new ExceptionResponse(m), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = BookAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleBookAlreadyExists(BookAlreadyExistsException e) {
        String m = String.format("Book with 'author' = '%s' and 'title' = '%s' already exists!",
                e.getAuthor(), e.getTitle());
        return new ResponseEntity<>(new ExceptionResponse(m), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = BookIdNotWritableException.class)
    public ResponseEntity<ExceptionResponse> handleBookIdIsNotWritable() {
        String m = "Property 'id' is readonly and will be generated automatically!";
        return new ResponseEntity<>(new ExceptionResponse(m), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders h, HttpStatus s, WebRequest r) {
        List<FieldError> errList = e.getBindingResult().getFieldErrors();

        Optional<FieldError> resolved = errList.stream()
                .filter(err -> Optional.ofNullable(err.getCode()).orElse("").equals("NotNull"))
                .findFirst();
        String m = resolved.isPresent() ? resolved.get().getDefaultMessage() : errList.get(0).getDefaultMessage();
        return new ResponseEntity<>(new ExceptionResponse(m), HttpStatus.BAD_REQUEST);
    }
}
