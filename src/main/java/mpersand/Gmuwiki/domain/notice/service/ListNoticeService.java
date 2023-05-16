package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeListResponse;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;

import java.util.List;
import java.util.stream.Collectors;

import static mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeResponse.toResponse;

@RequiredArgsConstructor
@ReadOnlyService
public class ListNoticeService {

    private final NoticeRepository noticeRepository;

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
}