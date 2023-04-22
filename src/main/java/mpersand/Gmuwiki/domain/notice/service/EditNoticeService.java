package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.exception.NoticeNotFoundException;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.EditNoticeRequest;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.global.annotation.AnnotationExceptionService;

@RequiredArgsConstructor
@AnnotationExceptionService
public class EditNoticeService {
    private final NoticeRepository noticeRepository;

    public void execute(Long id, EditNoticeRequest editNoticeRequest){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new NoticeNotFoundException());
        notice.update(editNoticeRequest);
    }
}
