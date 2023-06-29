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

        String content="<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Document</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div style='margin: 100px'>\n" +
                "      <h1>\n" +
                "        안녕하세요!\n" +
                "        <p style='color: #007eff; margin: 0; padding: 0; display: inline'>\n" +
                "          G무위키\n" +
                "        </p>\n" +
                "        입니다!\n" +
                "      </h1>\n" +
                "      <br />\n" +
                "      <h2>G무위키를 이용해주셔서 감사합니다. 귀하가 신청하신</h2> " + inquiryName + " <h2>에 대한 문의가 거부되었습니다.</h2>\n" +
                "      <br />\n" +
                "      <div\n" +
                "        align='center'\n" +
                "        style='\n" +
                "          border: 0px solid black;\n" +
                "          font-family: verdana;\n" +
                "          border: 1px solid #000;\n" +
                "          box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n" +
                "          border-radius: 5px;\n" +
                "        '\n" +
                "      >\n" +
                "        <h3 style='color: #007eff'>거부된 사유는 다음과 같습니다.</h3>\n" +
                "        <div style='font-size: 100%'>\n" +
                "            거부 사유 : <strong> " + comment + " </strong>\n" +
                "          <div><br /></div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "      <br />\n" +
                "        추가로 궁금한 사항이 있다면 디스코드 최민욱#9522으로 보내주시면 최대한\n" +
                "        빠르게 확인하겠습니다. 감사합니다.\n" +
                "      \n" +
                "  </body>\n" +
                "</html>";


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