package mpersand.Gmuwiki.domain.notice.service.impl;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.exception.EmailMIsmathchException;
import mpersand.Gmuwiki.domain.notice.exception.NoticeNotFoundException;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.domain.notice.service.RemoveNoticeService;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveNoticeServiceImpl implements RemoveNoticeService {
    private final NoticeRepository noticeRepository;
    private final UserUtil userUtil;

    @Override
    public void excute(Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()->new NoticeNotFoundException("게시물을 찾을 수 없습니다"));
        User user = userUtil.currentUser();
        if(notice.getUser().getEmail().equals(user.getEmail())){
            noticeRepository.deleteById(id);
        }
        else{
            throw new EmailMIsmathchException("이메일이 일치하지 않습니다");
        }
    }
}
