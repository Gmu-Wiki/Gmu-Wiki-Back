package mpersand.Gmuwiki.domain.inquiry.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;

@Getter
@Builder
@AllArgsConstructor
public class InquiryResponse {

    private String title;

    public static InquiryResponse toResponse(Inquiry inquiry) {

        return InquiryResponse.builder()
                .title(inquiry.getTitle())
                .build();
    }
}