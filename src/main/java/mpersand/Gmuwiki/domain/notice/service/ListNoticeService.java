package mpersand.Gmuwiki.domain.notice.service;

import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeResponse;

import java.util.List;

public interface ListNoticeService {
    List<NoticeResponse> execute();
}
