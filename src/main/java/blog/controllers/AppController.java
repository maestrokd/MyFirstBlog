package blog.controllers;


import blog.model.entity.Post;
import blog.model.service.PostServiceImpl;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    PostServiceImpl postServiceImpl;
    @Autowired
    Logger logger;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView();
//        List<Post> postList = postServiceImpl.findAll();
        List<Post> postList = new ArrayList<>();
        modelAndView.addObject("postList", postList);

        modelAndView.setViewName("index");
        return modelAndView;
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView getIndex() {
//        ModelAndView modelAndView = new ModelAndView();
//        List<Post> postList = postServiceImpl.findAll();
//        modelAndView.addObject("postList", postList);
//
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }


    // for 403 access denied page
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("accessDenied");
        return model;

    }

}
