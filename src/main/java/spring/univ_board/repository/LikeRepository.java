package spring.univ_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.univ_board.domain.Like;
import spring.univ_board.domain.User;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByUser(User user);
}
