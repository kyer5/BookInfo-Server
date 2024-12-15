package spring.univ_board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.controller.dto.ResponseDto;
import spring.univ_board.controller.dto.request.LikeRequest;
import spring.univ_board.controller.dto.response.LikeResponse;
import spring.univ_board.domain.User;
import spring.univ_board.service.LikeService;

@Controller
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseDto<LikeResponse> addItem(HttpSession session, @RequestBody LikeRequest likeRequest) {
        User user = (User) session.getAttribute("user");
        LikeResponse likeResponse = likeService.addItem(user, likeRequest);
        return ResponseDto.of(likeResponse, "Successfully clicked the like button.");
    }
}
