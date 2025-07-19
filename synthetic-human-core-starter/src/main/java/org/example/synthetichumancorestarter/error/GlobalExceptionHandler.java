package org.example.synthetichumancorestarter.error;

import org.example.synthetichumancorestarter.command.QueueOverflowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldErrors().get(0);
        String message = error.getField() + ": " + error.getDefaultMessage();

        ErrorResponse response = new ErrorResponse(
                Instant.now().toString(),
                message,
                ErrorCode.VALIDATION_FAILED.name()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueueOverflowException.class)
    public ResponseEntity<ErrorResponse> handleQueueOverflow(QueueOverflowException ex) {
        ErrorResponse response = new ErrorResponse(
                Instant.now().toString(),
                ex.getMessage(),
                ErrorCode.QUEUE_OVERFLOW.name()
        );
        return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse(
                Instant.now().toString(),
                ex.getMessage(),
                ErrorCode.UNEXPECTED_ERROR.name()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
