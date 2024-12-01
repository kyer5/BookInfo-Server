package spring.univ_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.univ_board.domain.Cart;
import spring.univ_board.domain.User;

public interface CartRepository extends JpaRepository<Cart, String> {

    Cart findByUserAndItemIsbn(User user, String Itemisbn);
}
