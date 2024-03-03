package agregador.investimento.api.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import agregador.investimento.api.exceptions.dto.ErrorMessageDTO;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<ErrorMessageDTO> errorMessageDTO = new ArrayList<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> {
            @SuppressWarnings("null")
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            ErrorMessageDTO err = new ErrorMessageDTO(message, error.getField());
            errorMessageDTO.add(err);
        });

        return new ResponseEntity<>(errorMessageDTO, HttpStatus.BAD_REQUEST);
    }

}
