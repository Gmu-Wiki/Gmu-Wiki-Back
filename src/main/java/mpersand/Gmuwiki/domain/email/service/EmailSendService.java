package mpersand.Gmuwiki.domain.email.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.email.entity.EmailAuth;
import mpersand.Gmuwiki.domain.email.exception.EmailSendFailedException;
import mpersand.Gmuwiki.domain.email.exception.ManyRequestEmailAuthException;
import mpersand.Gmuwiki.domain.email.presentation.dto.request.EmailSendRequest;
import mpersand.Gmuwiki.domain.email.repository.EmailAuthRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final EmailAuthRepository emailAuthRepository;
    private final JavaMailSender javaMailSender;

    @Transactional(rollbackFor = Exception.class)
    public void execute(EmailSendRequest emailSendRequest) {

        Random random = new Random();

        String authKey = String.valueOf(random.nextInt(888888)+111111);

        sendAuthEmail(emailSendRequest.getEmail(), authKey);
    }

    private MimeMessage sendAuthEmail(String email, String authCode) {
        MimeMessage message = javaMailSender.createMimeMessage();

        String subject = "G무위키 인증번호!";

        String content="";
        content+= "<div style='margin:100px;'>";
        content+= "<h1> 안녕하세요 G무위키입니다! </h1>";
        content+= "<br>";
        content+= "<h2><p>아래 코드를 인증 페이지로 돌아가 입력해주세요. 이용해 주셔서 감사합니다!<p></h2>";
        content+= "<br>";
        content+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        content+= "<h3 style='color:blue;'>인증 코드는 다음과 같습니다!</h3>";
        content+= "<div style='font-size:130%'>";
        content+= "인증코드 : <strong>";
        content+= authCode +"</strong><div><br/> ";
        content+= "</div>";

        EmailAuth emailAuth = EmailAuth.builder()
                .email(email)
                .randomValue(authCode)
                .attemptCount(0)
                .authentication(false)
                .build();

        if(emailAuth.getAttemptCount() >= 3) {
            throw new ManyRequestEmailAuthException();
        }

        emailAuth.updateRandomValue(authCode);
        emailAuth.increaseAttemptCount();

        emailAuthRepository.save(emailAuth);

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
