package mpersand.Gmuwiki.domain.inquiry.repository;

import mpersand.Gmuwiki.domain.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
