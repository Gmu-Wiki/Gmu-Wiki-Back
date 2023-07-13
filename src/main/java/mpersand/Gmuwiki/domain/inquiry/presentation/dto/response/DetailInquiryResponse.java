package mpersand.Gmuwiki.domain.inquiry.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.inquiry.enums.InquiryType;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
public class DetailInquiryResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    private InquiryType inquiryType;

    private String name;
}
