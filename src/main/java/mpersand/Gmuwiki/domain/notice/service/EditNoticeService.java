package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.EditNoticeRequest;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.NoticeUtil;

@RequiredArgsConstructor
@RollbackService
public class EditNoticeService {

    private final NoticeUtil noticeUtil;

    public void execute(Long id, EditNoticeRequest editNoticeRequest){

        Notice notice = noticeUtil.findNoticeById(id);

        notice.update(editNoticeRequest);
    }
}
