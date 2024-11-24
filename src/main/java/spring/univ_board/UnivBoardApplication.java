package spring.univ_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // BaseEntity의 @EntityListeners가 작동하기 위해서 @EnableJpaAuditing이 필요
public class UnivBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnivBoardApplication.class, args);
	}

}
