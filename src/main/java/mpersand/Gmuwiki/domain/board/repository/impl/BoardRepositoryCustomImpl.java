package mpersand.Gmuwiki.domain.board.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.entity.QBoard;
import mpersand.Gmuwiki.domain.board.repository.BoardRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> findByTitle(String title) {

        return jpaQueryFactory
                .selectFrom(QBoard.board)
                .where(QBoard.board.title.contains(title))
                .orderBy(QBoard.board.title.asc())
                .limit(12)
                .fetch();
        }
    }
