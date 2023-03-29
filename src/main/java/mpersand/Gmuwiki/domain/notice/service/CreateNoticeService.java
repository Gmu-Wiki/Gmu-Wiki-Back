package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.CreateNoticeRequest;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateNoticeService{
    private final UserUtil userUtil;
    private final NoticeRepository noticeRepository;

    @Transactional(rollbackOn = Exception.class)
    public void execute(CreateNoticeRequest createNoticeRequest){
        User user = userUtil.currentUser();
        Notice notice = Notice.builder()
                .title(createNoticeRequest.getTitle())
                .content(createNoticeRequest.getContent())
                .name(createNoticeRequest.getName())
                .user(user)
                .build();
        noticeRepository.save(notice);
    }
}
