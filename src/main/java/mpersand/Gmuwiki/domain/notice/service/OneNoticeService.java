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
                .orElseThrow(() -> new NoticeNotFoundException("게시글을 찾을수 없습니다"));
        NoticeDetailResponse noticeDetailResponse = NoticeDetailResponse.builder()
                .id(notice.getId())
                .content(notice.getContent())
                .name(notice.getName())
                .title(notice.getTitle())
                .build();

        return noticeDetailResponse;
    }
}
