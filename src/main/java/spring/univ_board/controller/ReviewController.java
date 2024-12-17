package spring.univ_board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.controller.dto.ResponseDto;
import spring.univ_board.controller.dto.request.ReviewCreateRequest;
import spring.univ_board.controller.dto.response.ReviewCreateResponse;
import spring.univ_board.domain.User;
import spring.univ_board.service.ReviewService;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseDto<ReviewCreateResponse> createReview(HttpSession session, @RequestBody ReviewCreateRequest reviewCreateRequest) {
        User user = (User) session.getAttribute("user");
        ReviewCreateResponse reviewCreateResponse = reviewService.createReview(user, reviewCreateRequest);
        return ResponseDto.of(reviewCreateResponse, "Successfully reviewed Registered.");
    }
}