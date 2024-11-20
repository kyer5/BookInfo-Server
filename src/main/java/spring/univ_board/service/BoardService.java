package spring.univ_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.univ_board.dto.BoardDTO;
import spring.univ_board.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 도서 목록 조회
     */
    public List<BoardDTO> getList() {
        return boardRepository.getList();
    }

    /**
     * 도서 저장
     */
    public void save(BoardDTO boardDTO) {
        boardRepository.save(boardDTO);
    }

    /**
     * 도서 상세 정보 가져오기
     */
    public BoardDTO detail(Integer id) {
        return boardRepository.detail(id);
    }

    /**
     * 도서 정보 삭제하기
     */
    public void goDelete(Integer id) {
        boardRepository.goDelete(id);
    }

    /**
     * 도서 정보 수정하기
     */
    public void goUpdate(BoardDTO boardDTO) {
        boardRepository.goUpdate(boardDTO);
    }
}
