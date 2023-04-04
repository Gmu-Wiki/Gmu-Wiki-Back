package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeListResponse;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeResponse;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ListNoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public NoticeListResponse execute() {
        List<Notice> notices = noticeRepository.findAll();
        NoticeListResponse noticeListResponse = NoticeListResponse.builder()
                .list(
                        notices.stream()
                                .map(notice -> toResponse(notice))
                                .collect(Collectors.toList())
                )
                .build();
        return noticeListResponse;
    }

    private NoticeResponse toResponse(Notice notice) {
        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .name(notice.getName())
                .build();
    }
}