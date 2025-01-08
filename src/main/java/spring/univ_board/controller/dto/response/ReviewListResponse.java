package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
public class ReviewListResponse {

    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String reviewer;
    private LocalDate createdAt;

    @Builder
    public ReviewListResponse(Long id, String title, String content, Long userId, String reviewer, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.reviewer = reviewer;
        this.createdAt = createdAt;
    }
}
