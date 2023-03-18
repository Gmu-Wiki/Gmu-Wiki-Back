package mpersand.Gmuwiki.global.error;

import lombok.Getter;

@Getter
public class GimuwikiException extends RuntimeException {

    private ErrorCode errorCode;

    public GimuwikiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
