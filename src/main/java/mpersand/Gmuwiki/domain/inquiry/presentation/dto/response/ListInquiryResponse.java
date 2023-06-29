package mpersand.Gmuwiki.domain.inquiry.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ListInquiryResponse {

    private List<InquiryResponse> inquiryList;
}