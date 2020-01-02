package blog.controllers;


import blog.model.entity.Post;
import blog.model.entity.User;
import blog.model.service.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class PostManagerController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    PostServiceImpl postServiceImpl;

    @Autowired
    Logger logger;


    @RequestMapping (value = "all/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostRoom(
            @PathVariable("id")
                    int id
    ) {
        ModelAndView modelAndView = new ModelAndView();
        Post selectedPost = postServiceImpl.findPostById(id);
        modelAndView.addObject("selectedPost", selectedPost);
        User userByLogin = userServiceImpl.findUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("selectedUser", userByLogin);
        modelAndView.setViewName("post/postroom");
        return modelAndView;
    }


    @RequestMapping (value = "/all/posts/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdatePostForm(
            @PathVariable("id")
                    int id
    ) {
        ModelAndView modelAndView = new ModelAndView();
        Post updatedPost = postServiceImpl.findPostById(id);
        modelAndView.addObject("updatedPostForm", updatedPost);
        modelAndView.setViewName("post/postupdateform");
        return modelAndView;
    }


    @RequestMapping (value = "/all/posts/{id}/update", method = RequestMethod.POST)
    public ModelAndView doUpdatePost(
            @PathVariable("id")
                    int id
            , @ModelAttribute(name = "updatedPostForm")
                    Post updatedPostForm
            , RedirectAttributes redirectAttributes
    ) {
        updatedPostForm.setId(id);
        postServiceImpl.updatePost(updatedPostForm);
        ModelAndView modelAndView = new ModelAndView();
        redirectAttributes.addFlashAttribute("message", "Post updated successfully ");
        modelAndView.setViewName("redirect:/all/posts/" + id);
        return modelAndView;
    }


    @RequestMapping (value = "/protected/posts/{id}/delete", method = RequestMethod.GET)
    public ModelAndView doDeletePost(
            @PathVariable("id")
                    int id
            , @RequestParam String login
    ) {
        boolean resultDel = postServiceImpl.deletePost(id);
        System.out.println(resultDel);
        return new ModelAndView("redirect:/protected/users/" + login);
    }


    // Show add post form
    @RequestMapping(value = "/all/posts/add", method = RequestMethod.GET)
    public ModelAndView showAddPostForm(Model model) {
        Post addPost = new Post();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("postForm", addPost);
        modelAndView.setViewName("post/postaddform");
        return modelAndView;
    }


    // Do add post
    @RequestMapping(value = "/all/posts/add", method = RequestMethod.POST)
    public ModelAndView doAddPost(Model model
            , RedirectAttributes redirectAttributes
            ,@ModelAttribute(name = "postForm")
            Post addPost
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        postServiceImpl.savePost(addPost, auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/all/userhome");
        return modelAndView;
    }

}
