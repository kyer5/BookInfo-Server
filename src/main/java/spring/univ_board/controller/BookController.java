package spring.univ_board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.controller.dto.ResponseDto;
import spring.univ_board.controller.dto.response.BookListResponse;
import spring.univ_board.service.BookService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/search")
    public String search() {
        return "naverBookList";
    }

    @PostMapping("/book/list")
    public String bookList(@ModelAttribute("keyword") String keyword, Model model) throws Exception {
        List<BookListResponse> bookListResponse = bookService.getBookList(keyword);
        model.addAttribute("bookListResponse", bookListResponse);
        return "naverBookList";
    }

}
