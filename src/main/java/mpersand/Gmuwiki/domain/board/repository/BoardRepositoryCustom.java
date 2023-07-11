package mpersand.Gmuwiki.domain.board.repository;

import mpersand.Gmuwiki.domain.board.entity.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findByTitle(String title);
}