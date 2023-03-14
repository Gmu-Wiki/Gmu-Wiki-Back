package mpersand.Gmuwiki.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다.", 409),
    NOT_SAME_PASSWORD("두 비밀번호가 일치하지 않습니다.", 400),
    MISMATCH_PASSWORD("비밀번호가 일치하지 않습니다.", 400),
    NOT_VERIFY_EMAIL("검증되지 않은 이메일입니다." , 401),
    MANY_REQUEST_EMAIL_AUTH("15분에 최대 3번의 이메일 요청만 가능합니다." , 429),
    EXPIRE_EMAIL_CODE("이메일 인증번호 시간이 만료되었습니다.", 401),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", 404),
    TOKEN_IS_EXPIRED("토큰이 만료 되었습니다.", 401),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),
    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 리프레시 토큰입니다.", 404),
    BLACKLIST_ALREADY_EXIST("블랙리스트에 이미 등록되었습니다.",400),
    EMAIL_MISMATCH("이메일이 일치하지 않습니다", 400),
    POST_NOT_FOUND("게시물을 찾을수 없습니다", 404);

    private final String message;
    private final int status;
}
