package mpersand.Gmuwiki.domain.inquiry.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import mpersand.Gmuwiki.domain.inquiry.enums.InquiryType;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class InquiryResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    private InquiryType inquiryType;

    public static InquiryResponse toResponse(Inquiry inquiry) {

        return InquiryResponse.builder()
                .id(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .createdDate(inquiry.getCreatedDate())
                .inquiryType(inquiry.getInquiryType())
                .build();
    }
}