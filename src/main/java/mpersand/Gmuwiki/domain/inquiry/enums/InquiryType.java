package mpersand.Gmuwiki.domain.inquiry.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum InquiryType {
    DELETE, QUESTION, REPORT;

    @JsonCreator
    public static InquiryType from(String s) { return InquiryType.valueOf(s.toUpperCase()); }
}
