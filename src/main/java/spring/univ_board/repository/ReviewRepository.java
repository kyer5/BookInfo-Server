package spring.univ_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.univ_board.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
