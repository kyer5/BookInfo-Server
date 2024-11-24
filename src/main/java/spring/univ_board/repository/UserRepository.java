package spring.univ_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.univ_board.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
