package spring.univ_board.controller.dto.request;

import lombok.Getter;

@Getter
public class ReviewCreateRequest {

    private String isbn;
    private String title;
    private String content;
}
