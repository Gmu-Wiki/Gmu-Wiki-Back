package mpersand.Gmuwiki.domain.board.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BoardType {
    STUDENT, TEACHER, CLUB, MAJOR, INCIDENT, SCHEDULE;

    @JsonCreator
    public static BoardType from(String s) { return BoardType.valueOf(s.toUpperCase()); }
}
