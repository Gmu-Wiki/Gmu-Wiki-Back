package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.exception.EmailMIsmathchException;
import mpersand.Gmuwiki.domain.notice.exception.NoticeNotFoundException;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoveNoticeService {
    private final NoticeRepository noticeRepository;
    private final UserUtil userUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()->new NoticeNotFoundException("게시물을 찾을 수 없습니다"));
        User user = userUtil.currentUser();
        if(notice.getUser().equals(user)){
            noticeRepository.delete(notice);
        }
        else{
            throw new EmailMIsmathchException("이메일이 일치하지 않습니다");
        }
    }
}
