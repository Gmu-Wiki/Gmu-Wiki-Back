package mpersand.Gmuwiki.domain.inquiry.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.inquiry.enums.InquiryType;


@Getter
@Builder
@AllArgsConstructor
public class DetailInquiryResponse {

    private String title;

    private String content;

    private InquiryType inquiryType;

    private String name;
}
