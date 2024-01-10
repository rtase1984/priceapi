package klagan.priceapi.infrastructure.web;
import klagan.priceapi.infrastructure.exception.PriceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = PriceException.class)
    public ResponseEntity<ErrorResponse> handleMyCustomException(PriceException ex) {

        return ResponseEntity.status(ex.getErrorCode())
                .body(new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage()));
    }

    record ErrorResponse(HttpStatus code, String message) {
    }
}
