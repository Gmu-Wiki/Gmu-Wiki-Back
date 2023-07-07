package mpersand.Gmuwiki.domain.inquiry.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import mpersand.Gmuwiki.domain.inquiry.enums.InquiryType;

@Getter
@Builder
@AllArgsConstructor
public class InquiryResponse {

    private Long id;

    private String title;

    private InquiryType inquiryType;

    public static InquiryResponse toResponse(Inquiry inquiry) {

        return InquiryResponse.builder()
                .id(inquiry.getId())
                .title(inquiry.getTitle())
                .inquiryType(inquiry.getInquiryType())
                .build();
    }
}