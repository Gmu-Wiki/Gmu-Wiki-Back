package mpersand.Gmuwiki.domain.file.repository;

import mpersand.Gmuwiki.domain.file.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
}