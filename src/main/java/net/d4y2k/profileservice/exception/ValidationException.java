package net.d4y2k.profileservice.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationException extends RuntimeException{

    private final Map<String, String> errors = new HashMap<>();

    public ValidationException(BindingResult bindingResult) {
        bindingResult.getAllErrors().forEach(
                error -> errors.put(error.getObjectName(), error.getDefaultMessage())
        );
    }
}
