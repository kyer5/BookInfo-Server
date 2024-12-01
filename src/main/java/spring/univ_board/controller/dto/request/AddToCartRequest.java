package spring.univ_board.controller.dto.request;

import lombok.Getter;

@Getter
public class AddToCartRequest {

    private String isbn;
    private int itemCount;
}
