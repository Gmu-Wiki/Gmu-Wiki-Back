package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.exception.NoticeNotFoundException;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeDetailResponse;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.global.annotation.AnnotationReadOnlyService;

@RequiredArgsConstructor
@AnnotationReadOnlyService
public class OneNoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeDetailResponse execute(Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException());

        NoticeDetailResponse noticeDetailResponse = NoticeDetailResponse.builder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDate(notice.getCreatedDate())
                .editedDate(notice.getEditedDate())
                .build();

        return noticeDetailResponse;
    }
}
