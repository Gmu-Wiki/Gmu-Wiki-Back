package mpersand.Gmuwiki.domain.inquiry.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.response.ListInquiryResponse;
import mpersand.Gmuwiki.domain.inquiry.repository.InquiryRepository;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;

import java.util.List;
import java.util.stream.Collectors;

import static mpersand.Gmuwiki.domain.inquiry.presentation.dto.response.InquiryResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListInquiryService {

    private final InquiryRepository inquiryRepository;

    public ListInquiryResponse execute() {

        List<Inquiry> inquiries = inquiryRepository.findAll();

        ListInquiryResponse listInquiryResponse = ListInquiryResponse.builder()
                .inquiryList(
                        inquiries.stream()
                                .map(inquiry -> toResponse(inquiry))
                                .collect(Collectors.toList())
                )
                .build();

        return listInquiryResponse;
    }
}