package spring.univ_board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.controller.dto.ResponseDto;
import spring.univ_board.controller.dto.request.AddToCartRequest;
import spring.univ_board.controller.dto.response.AddToCartResponse;
import spring.univ_board.domain.User;
import spring.univ_board.service.CartService;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseDto<AddToCartResponse> addItem(HttpSession session, @RequestBody AddToCartRequest addToCartRequest) {
        User user = (User) session.getAttribute("user");
        AddToCartResponse addToCartResponse = cartService.addItem(user, addToCartRequest);
        return ResponseDto.of(addToCartResponse, "Successfully added product to cart.");
    }
}
