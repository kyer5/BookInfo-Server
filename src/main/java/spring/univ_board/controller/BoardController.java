package spring.univ_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.univ_board.dto.BoardDTO;
import spring.univ_board.service.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 도서 조회
     */
    @GetMapping("/list")
    public String getList(Model model) {
        List<BoardDTO> boardDTOS = boardService.getList();
        model.addAttribute("bookList", boardDTOS);
        return "bookList";
    }

    /**
     * 도서 추가 화면
     */
    @GetMapping("/addBook")
    public String addBook() {
        System.out.println("도서 추가 화면 컨트롤러");
        return "addBook";
    }

    /**
     * 도서 추가 (DB 저장)
     */
    @PostMapping("/addBook")
    public void save(BoardDTO boardDTO) {
        boardService.save(boardDTO);
    }

    /**
     * 도서 상세 정보 가져오기
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        BoardDTO boardDTO = boardService.detail(id);
        model.addAttribute("bookDetail", boardDTO);
        return "detailBook";
    }

    /**
     * 도서 정보 삭제하기
     */
    @GetMapping("/goDelete/{id}")
    public String goDelete(@PathVariable("id") Integer id) {
        boardService.goDelete(id);
        return "redirect:/list";
    }

    /**
     * 도서 정보 수정 화면 호출
     */
    @GetMapping("/goUpdate/{id}")
    public String goUpdate(@PathVariable("id") Integer id, Model model) {
        BoardDTO boardDTO = boardService.detail(id);
        model.addAttribute("bookDetail", boardDTO);
        return "updateBook";
    }

    /**
     * 도서 정보 수정
     */
    @PostMapping("/goUpdate/{id}")
    public String goUpdate(BoardDTO boardDTO, Model model) {
        boardService.goUpdate(boardDTO); // 업데이트 작업 요청

        // update 완료 후, 수정된 내용을 다시 조회
        BoardDTO dto = boardService.detail(boardDTO.getBookid());
        model.addAttribute("bookDetail", dto);
        return "detailBook";
    }
}
