package mpersand.Gmuwiki.global.error.handler;

import lombok.extern.slf4j.Slf4j;
import mpersand.Gmuwiki.domain.auth.exception.ExistEmailException;
import mpersand.Gmuwiki.domain.auth.exception.MisMatchPasswordException;
import mpersand.Gmuwiki.domain.auth.exception.UserNotFoundException;
import mpersand.Gmuwiki.domain.email.exception.EmailSendFailedException;
import mpersand.Gmuwiki.domain.email.exception.ManyRequestEmailAuthException;
import mpersand.Gmuwiki.domain.email.exception.NotVerifyEmailException;
import mpersand.Gmuwiki.global.error.ErrorMessage;
import mpersand.Gmuwiki.global.security.exception.TokenExpirationException;
import mpersand.Gmuwiki.global.security.exception.TokenNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenExpirationException.class)
    public ResponseEntity<ErrorMessage> handleTokenExpirationException(HttpServletRequest request, TokenExpirationException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<ErrorMessage> handleNotSamePasswordException(HttpServletRequest request, TokenNotValidException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(HttpServletRequest request, UserNotFoundException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ExistEmailException.class)
    public ResponseEntity<ErrorMessage> handleExistEmailException(HttpServletRequest request, ExistEmailException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MisMatchPasswordException.class)
    public ResponseEntity<ErrorMessage> handleMisMatchPasswordException(HttpServletRequest request, MisMatchPasswordException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotVerifyEmailException.class)
    public ResponseEntity<ErrorMessage> handleNotVerifyEmailException(HttpServletRequest request, NotVerifyEmailException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ManyRequestEmailAuthException.class)
    public ResponseEntity<ErrorMessage> handleManyRequestEmailException(HttpServletRequest request, ManyRequestEmailAuthException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(EmailSendFailedException.class)
    public ResponseEntity<ErrorMessage> handleEmailSendFailedException(HttpServletRequest request, EmailSendFailedException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, RuntimeException ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }
}
