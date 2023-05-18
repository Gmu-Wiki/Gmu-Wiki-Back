package mpersand.Gmuwiki.domain.inquiry.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import mpersand.Gmuwiki.domain.inquiry.enums.InquiryType;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquirySendRequest;
import mpersand.Gmuwiki.domain.inquiry.repository.InquiryRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.UserUtil;

import java.time.LocalDateTime;

@RollbackService
@RequiredArgsConstructor
public class InquirySendService {

    private final UserUtil userUtil;

    private final InquiryRepository inquiryRepository;

    public void execute(InquirySendRequest inquirySendRequest) {

        User user = userUtil.currentUser();

        Inquiry inquiry = Inquiry.builder()
                .title(inquirySendRequest.getTitle())
                .content(inquirySendRequest.getContent())
                .name(user.getName())
                .user(user)
                .inquiryType(InquiryType.from(inquirySendRequest.getInquiryType()))
                .createdDate(LocalDateTime.now())
                .build();

        inquiryRepository.save(inquiry);
    }
}
