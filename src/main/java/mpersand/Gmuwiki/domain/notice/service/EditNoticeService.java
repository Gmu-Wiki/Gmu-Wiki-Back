package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.exception.NoticeNotFoundException;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.EditNoticeRequest;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditNoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long id, EditNoticeRequest editNoticeRequest){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new NoticeNotFoundException("게시글을 찾을수 없습니다"));
        notice.update(editNoticeRequest);
    }
}
