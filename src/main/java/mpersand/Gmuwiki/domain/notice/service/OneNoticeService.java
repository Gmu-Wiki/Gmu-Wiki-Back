package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.exception.NoticeNotFoundException;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeDetailResponse;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OneNoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
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
