package blog.model.repository;

import blog.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(int id);

    List<Post> findPostsByUserLogin(String login);

}



