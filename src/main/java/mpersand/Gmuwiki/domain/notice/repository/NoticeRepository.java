package mpersand.Gmuwiki.domain.notice.repository;

import mpersand.Gmuwiki.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
