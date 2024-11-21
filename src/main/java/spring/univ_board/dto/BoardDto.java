package spring.univ_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {

    private int bookid;
    private String bookname;
    private String publisher;
    private int price;

}
