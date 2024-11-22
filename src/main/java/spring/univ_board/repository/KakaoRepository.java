package spring.univ_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.univ_board.domain.KakaoUser;

@Repository
public interface KakaoRepository extends JpaRepository<KakaoUser, Long> {

    KakaoUser save(KakaoUser kakaoUser);
}
