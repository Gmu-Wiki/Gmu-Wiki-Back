package mpersand.Gmuwiki.global.error.handler;

import mpersand.Gmuwiki.global.error.ErrorMessage;
import mpersand.Gmuwiki.global.error.GimuwikiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @ExceptionHandler(GimuwikiException.class)
    public ResponseEntity<ErrorMessage> handleComprehensiveException(HttpServletRequest request, GimuwikiException e) {
        printError(request, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        printError(request, "요청값이 유효하지 않습니다.");
        ErrorMessage errorMessage = new ErrorMessage("요청값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


    private void printError(HttpServletRequest request, String message) {
        log.error(request.getRequestURI());
        log.error(message);
    }
}