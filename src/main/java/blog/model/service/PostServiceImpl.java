package blog.model.service;

import blog.model.entity.*;
import blog.model.repository.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl {

    // Fields
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    Logger logger;

    // Setters
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    // Methods
    public List<Post> findAll() {
        return postRepository.findAll().stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
//                .limit(5)
                .collect(Collectors.toList());
    }


    public Post findPostById(int id) {
        return postRepository.findPostById(id);
    }

    public List<Post> findPostsForUser(String login) {
        return postRepository.findPostsByUserLogin(login);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public void savePost(Post post, String login) {
        User user = userRepository.findUserByLogin(login);
        post.setUser(user);
        postRepository.save(post);
    }


    public boolean updatePost(Post updatedPostForm) {
        Post postDB = postRepository.findPostById(updatedPostForm.getId());
        postDB.setTitle(updatedPostForm.getTitle());
        postDB.setBody(updatedPostForm.getBody());
        postRepository.save(postDB);
        return true;
    }


    public boolean deletePost(int id) {
        Post post = postRepository.findPostById(id);
        postRepository.delete(post);
        return true;
    }

}
