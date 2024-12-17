package spring.univ_board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.controller.dto.ResponseDto;
import spring.univ_board.controller.dto.request.LikeRequest;
import spring.univ_board.controller.dto.response.LikeResponse;
import spring.univ_board.controller.dto.response.LikeListResponse;
import spring.univ_board.domain.User;
import spring.univ_board.service.LikeService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseDto<LikeResponse> addLike(HttpSession session, @RequestBody LikeRequest likeRequest) {
        User user = (User) session.getAttribute("user");
        LikeResponse likeResponse = likeService.addLike(user, likeRequest);
        return ResponseDto.of(likeResponse, "Successfully clicked the like button.");
    }

    @GetMapping("/list")
    public String cartList(HttpSession session, Model model) throws IOException {
        User user = (User) session.getAttribute("user");
        List<LikeListResponse> likeListResponses = likeService.getLikeList(user);
        model.addAttribute("likeListResponses", likeListResponses);
        return "like/likeList";
    }

    @DeleteMapping("/cancel")
    @ResponseBody
    public ResponseDto<LikeResponse> cancelLike(@RequestParam("likeId") Long likeId) {
        LikeResponse likeResponse = likeService.cancelLike(likeId);
        return ResponseDto.of(likeResponse, "Successfully canceled like.");
    }
}
