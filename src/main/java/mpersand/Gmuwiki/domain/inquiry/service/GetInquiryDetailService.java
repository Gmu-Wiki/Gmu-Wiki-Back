package mpersand.Gmuwiki.domain.inquiry.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import mpersand.Gmuwiki.domain.inquiry.exception.InquiryNotFoundException;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.response.DetailInquiryResponse;
import mpersand.Gmuwiki.domain.inquiry.repository.InquiryRepository;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;

@ReadOnlyService
@RequiredArgsConstructor
public class GetInquiryDetailService {

    private final InquiryRepository inquiryRepository;

    public DetailInquiryResponse execute(Long id) {

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new InquiryNotFoundException());

        DetailInquiryResponse detailInquiryResponse = DetailInquiryResponse.builder()
                .id(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .inquiryType(inquiry.getInquiryType())
                .name(inquiry.getName())
                .build();

        return detailInquiryResponse;
    }
}