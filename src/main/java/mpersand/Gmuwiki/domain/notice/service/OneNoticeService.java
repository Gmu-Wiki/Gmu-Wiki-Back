package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeDetailResponse;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;
import mpersand.Gmuwiki.global.util.NoticeUtil;

@RequiredArgsConstructor
@ReadOnlyService
public class OneNoticeService {

    private final NoticeUtil noticeUtil;

    public NoticeDetailResponse execute(Long id) {

        Notice notice = noticeUtil.findNoticeById(id);

        NoticeDetailResponse noticeDetailResponse = NoticeDetailResponse.builder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDate(notice.getCreatedDate())
                .editedDate(notice.getEditedDate())
                .build();

        return noticeDetailResponse;
    }
}
