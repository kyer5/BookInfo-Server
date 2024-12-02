package spring.univ_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.univ_board.domain.Cart;
import spring.univ_board.domain.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserAndItemIsbn(User user, String ItemIsbn);
    List<Cart> findByUser(User user);
}
