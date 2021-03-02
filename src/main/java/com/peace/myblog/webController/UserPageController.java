package com.peace.myblog.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YR#
 * @create 2020-08-13 23:24
 */
@Controller
@RequestMapping("/user")
public class UserPageController {



    @GetMapping("/index")
    public String index() {

        return "index";
    }

    @GetMapping("/category")
    public String category() {

        return "categories";
    }

    @GetMapping("/aboutMe")
    public String about() {

        return "aboutMe";
    }

    @GetMapping("/tag")
    public String tag() {

        return "tags";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/timeline")
    public String timeLine() {

        return "timeline";
    }

    @GetMapping("/categoryName/{categoryName}")
    public String categoryName(){

        return "category_name";
    }

    @GetMapping("/tag/{tagName}")
    public String listBlogByTag(){

        return "tags";
    }

    @GetMapping("/time/{createTime}")
    public String timeLineDate() {

        return "time";
    }

    @GetMapping("/blogRecord")
    public String blogRecord() {

        return "blogRecord";
    }




    @GetMapping("/friendLink")
    public String friendLink() {

        return "friendLink";
    }

    @GetMapping("/travel")
    public String travel() {

        return "travel";
    }

    @GetMapping("/getBlogDetail/{id}")
    public String readBlog() {
        return "article";
    }






    @GetMapping("/signIn")
    public String loginPage() {
        return "login";
    }




}
