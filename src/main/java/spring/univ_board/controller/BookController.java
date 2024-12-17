package spring.univ_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.controller.dto.response.BookDetailsResponse;
import spring.univ_board.controller.dto.response.BookListResponse;
import spring.univ_board.controller.dto.response.ReviewListResponse;
import spring.univ_board.service.BookService;
import spring.univ_board.service.ReviewService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping("/list")
    public String bookList(@ModelAttribute("keyword") String keyword, Model model) throws IOException {
        List<BookListResponse> bookListResponse = bookService.getBookList(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("bookListResponse", bookListResponse);
        return "book/bookList";
    }

    @GetMapping("/detail")
    public String bookDetails(@RequestParam String isbn, Model model) throws IOException {
        BookDetailsResponse bookDetailsResponse = bookService.getBookDetails(isbn);
        model.addAttribute("bookDetailsResponse", bookDetailsResponse);
        List<ReviewListResponse> reviewListResponses = reviewService.getReviewList(isbn);
        model.addAttribute("reviewListResponses", reviewListResponses);
        return "book/detailsBook";
    }
}
