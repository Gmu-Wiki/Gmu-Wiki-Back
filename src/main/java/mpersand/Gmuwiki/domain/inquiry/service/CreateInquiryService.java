package mpersand.Gmuwiki.domain.inquiry.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.file.entity.BoardFile;
import mpersand.Gmuwiki.domain.file.repository.BoardFileRepository;
import mpersand.Gmuwiki.domain.file.service.BoardFileService;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import mpersand.Gmuwiki.domain.inquiry.enums.InquiryType;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquiryWriteRequest;
import mpersand.Gmuwiki.domain.inquiry.repository.InquiryRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RollbackService
@RequiredArgsConstructor
public class CreateInquiryService {

    @Value("${cloud.aws.s3.url}")
    private String AWS_S3_ADDRESS;

    private final UserUtil userUtil;

    private final InquiryRepository inquiryRepository;

    private final BoardFileService boardFileService;

    private final BoardFileRepository boardFileRepository;

    public void execute(InquiryWriteRequest inquiryWriteRequest, List<MultipartFile> multipartFileList) {

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

        if (multipartFileList != null) {
            List<String> uploadFile = boardFileService.uploadFile(multipartFileList);
            for (String uploadFileUrl : uploadFile) {
                boardFileRepository.save(saveToUrl(inquiry, uploadFileUrl));
            }
        }
    }

    private BoardFile saveToUrl(Inquiry inquiry, String uploadFileUrl) {

        BoardFile boardFile = BoardFile.builder()
                .inquiry(inquiry)
                .url(AWS_S3_ADDRESS + uploadFileUrl)
                .build();

        return boardFile;
    }
}