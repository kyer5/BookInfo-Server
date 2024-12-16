package spring.univ_board.controller.dto.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookRecommendRequest {

    List<String> recommendIsbn = new ArrayList<>();

    public BookRecommendRequest() {
        recommendIsbn.add("9791191905717");
        recommendIsbn.add("9791162244739");
        recommendIsbn.add("9791169211864");
        recommendIsbn.add("9791140711444");
        recommendIsbn.add("9791140708116");
        recommendIsbn.add("9791158395513");
        recommendIsbn.add("9791162243091");
        recommendIsbn.add("9791169213073");
        recommendIsbn.add("9788966260959");
        recommendIsbn.add("9791158391744");
    }
}
