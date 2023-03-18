package mpersand.Gmuwiki.domain.notice.service;

import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeIdResponse;

public interface OneNoticeService {
    NoticeIdResponse execute(Long id);
}
