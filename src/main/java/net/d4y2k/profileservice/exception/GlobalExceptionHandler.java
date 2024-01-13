package net.d4y2k.profileservice.exception;

import net.d4y2k.profileservice.profile.UploadFileException;
import net.d4y2k.profileservice.utils.files.FileNameIsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNameIsEmptyException.class)
    ResponseEntity<String> handleFileNameIsEmptyException(FileNameIsEmptyException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UploadFileException.class)
    ResponseEntity<String> handleUploadFileException(UploadFileException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<Map<String, String>> handleValidationException(ValidationException exception) {
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.BAD_REQUEST);
    }

}
