package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.NoticeUtil;

@RequiredArgsConstructor
@RollbackService
public class DeleteNoticeService {

    private final NoticeRepository noticeRepository;

    private final NoticeUtil noticeUtil;

    public void execute(Long id){

        Notice notice = noticeUtil.findNoticeById(id);

        noticeRepository.delete(notice);
    }
}
