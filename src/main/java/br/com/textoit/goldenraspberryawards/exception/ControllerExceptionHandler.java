package br.com.textoit.goldenraspberryawards.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.webjars.NotFoundException;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MensageError handleExceptions(MethodArgumentNotValidException ex) {
        logError(ex);
        final var listErrors = ex.getBindingResult().getAllErrors().parallelStream()
                .map(objectError -> String.format("%s", ((objectError).getDefaultMessage())))
                .toList();

        return new MensageError(BAD_REQUEST.value(), "error fields", listErrors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public final MensageError handleExceptions(NotFoundException ex, WebRequest request){
        logError(ex);
        return MensageError.to(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }

    private void logError(final Exception e) {
        final String message = String.format("[e]=%s,[m]=%s", e.getClass().getSimpleName(), e.getMessage());
        log.error(message, e);
    }

}
