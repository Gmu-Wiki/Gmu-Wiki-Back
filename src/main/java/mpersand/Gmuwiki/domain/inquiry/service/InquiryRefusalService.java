package mpersand.Gmuwiki.domain.inquiry.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.exception.EmailSendFailedException;
import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import mpersand.Gmuwiki.domain.inquiry.exception.InquiryNotFoundException;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquirySendRequest;
import mpersand.Gmuwiki.domain.inquiry.repository.InquiryRepository;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RollbackService
@RequiredArgsConstructor
public class InquiryRefusalService {

    private final InquiryRepository inquiryRepository;

    private final JavaMailSender javaMailSender;

    public void execute(Long id, InquirySendRequest inquirySendRequest) {

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new InquiryNotFoundException());

        String email = inquiry.getUser().getEmail();

        String inquiryName = inquiry.getTitle();

        sendInquiryEmail(email, inquiryName, inquirySendRequest.getComment());

        inquiryRepository.delete(inquiry);
    }

    private MimeMessage sendInquiryEmail(String email, String inquiryName, String comment) {
        MimeMessage message = javaMailSender.createMimeMessage();

        String subject = "G무위키 문의 답변이 도착했습니다!";

        String content="";
        content+= "<div style='margin:100px;'>";
        content+= "<h1> 안녕하세요 G무위키입니다! </h1>";
        content+= "<br>";
        content+= "<h2><p>G무위키를 이용해주셔서 감사합니다. 귀하가 신청하신<p></h2>" + inquiryName + "<h2><p>에 대한 문의가 거부되었습니다.";
        content+= "<br>";
        content+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        content+= "<h3 style='color:blue;'>거부된 사유는 다음과 같습니다.</h3>";
        content+= "<div style='font-size:100%'>";
        content+= "거부 사유 : <strong>";
        content+= comment +"</strong><div><br/> ";
        content+= "</div>";
        content+= "</div>";
        content+= "</div>";
        content+= "<br>";
        content+= "<p>추가로 궁금한 사항이 있다면 디스코드 최민욱#9522으로 보내주시면 최대한 빠르게 확인하겠습니다. 감사합니다.<p>";

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendFailedException();
        }

        return message;
    }
}