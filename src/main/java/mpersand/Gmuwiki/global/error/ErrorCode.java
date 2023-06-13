package mpersand.Gmuwiki.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //SERVER ERROR
    UNKNOWN_ERROR("알 수 없는 에러입니다.", 500),
    EMAIL_SEND_FAIL("메일 발송에 실패했습니다", 500),

    //TOKEN
    TOKEN_IS_EXPIRED("토큰이 만료 되었습니다.", 401),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),

    //BOARD
    BOARD_NOT_FOUND("게시글을 찾을 수 없습니다.", 404),
    MISMATCH_BOARD_AUTHOR("내가 작성한 글이 아닙니다.", 403),
    ALREADY_EXIST_TITLE("이미 존재하는 제목입니다.", 409),

    //BOARD RECORD
    BOARD_RECORD_NOT_FOUND("게시글의 기록을 찾을 수 없습니다.", 404),

    //NOTICE
    NOTICE_NOT_FOUND("공지글을 찾을 수 없습니다", 404),

    //FILE
    NOT_ALLOWED_FILE("허용되지 않은 파일 형식입니다.", 400),
    FILE_UPLOAD_FAIL("파일 업로드에 실패했습니다.", 500),
    INVALID_FORMAT_FILE("잘못된 형식의 파일입니다.", 400),

    //USER
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", 404),
    ROLE_NOT_EXIST("역할이 존재하지 않습니다.", 404),

    //INQUIRY
    INQUIRY_NOT_FOUND("문의 사항을 찾을 수 없습니다.", 404);

    private final String message;
    private final int status;
}
