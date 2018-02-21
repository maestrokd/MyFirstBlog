package blog.model.repository;

import blog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy[real field name]!!!
    User findUserByLogin(String login);

    User findUserByEmail(String email);
}



