package klagan.priceapi.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class PriceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private HttpStatus errorCode;
    private String errorMessage;

}

