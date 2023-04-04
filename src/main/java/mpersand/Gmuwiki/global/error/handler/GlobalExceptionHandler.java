package mpersand.Gmuwiki.global.error.handler;

import lombok.extern.slf4j.Slf4j;
import mpersand.Gmuwiki.global.error.ErrorMessage;
import mpersand.Gmuwiki.global.error.GimuwikiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

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