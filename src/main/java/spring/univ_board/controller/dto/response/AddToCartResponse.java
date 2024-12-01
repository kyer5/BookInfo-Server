package spring.univ_board.controller.dto.response;

import lombok.Getter;

@Getter
public class AddToCartResponse {

    private Long cartId;

    public AddToCartResponse(Long cartId) {
        this.cartId = cartId;
    }
}
