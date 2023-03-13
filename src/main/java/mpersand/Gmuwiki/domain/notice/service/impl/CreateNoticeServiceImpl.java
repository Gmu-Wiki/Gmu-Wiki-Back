package mpersand.Gmuwiki.domain.notice.service.impl;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.CreateNoticeRequest;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.domain.notice.service.CreateNoticeService;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateNoticeServiceImpl implements CreateNoticeService {
    private final UserUtil userUtil;
    private final NoticeRepository noticeRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(CreateNoticeRequest createNoticeRequest){
        User user = userUtil.currentUser();

        Notice notice = Notice.builder()
                .title(createNoticeRequest.getTitle())
                .content(createNoticeRequest.getContent())
                .name(createNoticeRequest.getName())
                .user(user)
                .build();
    }
}
