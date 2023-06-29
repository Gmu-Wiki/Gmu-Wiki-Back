package mpersand.Gmuwiki.global.error;

import lombok.Getter;

@Getter
public class GmuwikiException extends RuntimeException {

    private ErrorCode errorCode;

    public GmuwikiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
