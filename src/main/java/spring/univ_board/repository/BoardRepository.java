package spring.univ_board.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import spring.univ_board.dto.BoardDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;

    /**
     * 도서 목록 조회
     */
    public List<BoardDto> getList() {
        return sql.selectList("Board.getList"); // Board -> mapper. namespace = "Board"
    }

    /**
     * 도서 저장
     */
    public void save(BoardDto boardDto) {
        sql.insert("Board.save", boardDto);
    }

    /**
     * 도서 상세 정보
     */
    public BoardDto detail(Integer id) {
        return sql.selectOne("Board.detail", id);
    }

    public void goDelete(Integer id) {
        sql.delete("Board.goDelete", id);
    }

    public void goUpdate(BoardDto boardDTO) {
        sql.update("Board.goUpdate", boardDTO);
    }
}
