package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.CreateNoticeRequest;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateNoticeService {
    private final UserUtil userUtil;
    private final NoticeRepository noticeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(CreateNoticeRequest createNoticeRequest){
        User user = userUtil.currentUser();

        Notice notice = Notice.builder()
                .title(createNoticeRequest.getTitle())
                .content(createNoticeRequest.getContent())
                .name(user.getName())
                .user(user)
                .createdDate(LocalDateTime.now())
                .editedDate(LocalDateTime.now())
                .build();
        noticeRepository.save(notice);
    }
}
