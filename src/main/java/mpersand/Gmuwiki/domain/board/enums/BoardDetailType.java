package mpersand.Gmuwiki.domain.board.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BoardDetailType {

    FIFTH, SIXTH, SEVENTH,
    GENERAL, SPECIALITY, OTHER,
    MAJOR, CA,
    MAJORS,
    TWENTY_FIRST, TWENTY_SECOND, TWENTY_THIRD,
    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEPT, OCT, NOV, DEC,
    TEAM, INDIVIDUAL;

    @JsonCreator
    public static BoardDetailType from(String s) { return BoardDetailType.valueOf(s.toUpperCase()); }
}