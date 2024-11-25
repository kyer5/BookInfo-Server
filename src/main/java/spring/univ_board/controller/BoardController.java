package spring.univ_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.univ_board.dto.BoardDto;
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
        List<BoardDto> boardDtos = boardService.getList();
        model.addAttribute("bookList", boardDtos);
        return "book/bookList";
    }

    /**
     * 도서 추가 화면
     */
    @GetMapping("/addBook")
    public String addBook() {
        System.out.println("도서 추가 화면 컨트롤러");
        return "book/addBook";
    }

    /**
     * 도서 추가 (DB 저장)
     */
    @PostMapping("/addBook")
    public void save(BoardDto boardDto) {
        boardService.save(boardDto);
    }

    /**
     * 도서 상세 정보 가져오기
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        BoardDto boardDto = boardService.detail(id);
        model.addAttribute("bookDetail", boardDto);
        return "book/detailBook";
    }

    /**
     * 도서 정보 삭제하기
     */
    @GetMapping("/goDelete/{id}")
    public String goDelete(@PathVariable("id") Integer id) {
        boardService.goDelete(id);
        return "book/redirect:/list";
    }

    /**
     * 도서 정보 수정 화면 호출
     */
    @GetMapping("/goUpdate/{id}")
    public String goUpdate(@PathVariable("id") Integer id, Model model) {
        BoardDto boardDto = boardService.detail(id);
        model.addAttribute("bookDetail", boardDto);
        return "book/updateBook";
    }

    /**
     * 도서 정보 수정
     */
    @PostMapping("/goUpdate/{id}")
    public String goUpdate(BoardDto boardDto, Model model) {
        boardService.goUpdate(boardDto); // 업데이트 작업 요청

        // update 완료 후, 수정된 내용을 다시 조회
        BoardDto dto = boardService.detail(boardDto.getBookid());
        model.addAttribute("bookDetail", dto);
        return "book/detailBook";
    }
}
