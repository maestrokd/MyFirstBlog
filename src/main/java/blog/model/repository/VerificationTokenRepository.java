package blog.model.repository;


import blog.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findVerificationTokenByToken(String token);

    VerificationToken findVerificationTokenByUserLogin(String login);

}



