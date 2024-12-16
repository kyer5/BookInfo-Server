package spring.univ_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.univ_board.controller.dto.request.BookRecommendRequest;
import spring.univ_board.controller.dto.response.BookRecommendListResponse;
import spring.univ_board.service.BookService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BookService bookService;

    @GetMapping("/")
    public String bookRecommend(Model model) throws IOException {
        BookRecommendRequest bookRecommendRequest = new BookRecommendRequest();
        List<BookRecommendListResponse> bookRecommendListResponses = bookService.bookRecommend(bookRecommendRequest);
        model.addAttribute("bookRecommendListResponses", bookRecommendListResponses);
        return "index";
    }
}
