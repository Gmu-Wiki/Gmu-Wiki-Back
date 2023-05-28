package mpersand.Gmuwiki.domain.inquiry.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import mpersand.Gmuwiki.domain.inquiry.enums.InquiryType;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquiryWriteRequest;
import mpersand.Gmuwiki.domain.inquiry.repository.InquiryRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.UserUtil;

import java.time.LocalDateTime;

@RollbackService
@RequiredArgsConstructor
public class CreateInquiryService {

    private final UserUtil userUtil;

    private final InquiryRepository inquiryRepository;

    public void execute(InquiryWriteRequest inquiryWriteRequest) {

        User user = userUtil.currentUser();

        Inquiry inquiry = Inquiry.builder()
                .title(inquiryWriteRequest.getTitle())
                .content(inquiryWriteRequest.getContent())
                .name(user.getName())
                .user(user)
                .inquiryType(InquiryType.from(inquiryWriteRequest.getInquiryType()))
                .createdDate(LocalDateTime.now())
                .build();

        inquiryRepository.save(inquiry);
    }
}