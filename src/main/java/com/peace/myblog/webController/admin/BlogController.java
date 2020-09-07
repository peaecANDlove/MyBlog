package com.peace.myblog.webController.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.Blog;
import com.peace.myblog.dto.BlogModel;
import com.peace.myblog.dto.BlogSeach;
import com.peace.myblog.dto.UserModel;
import com.peace.myblog.service.BlogService;
import com.peace.myblog.service.CategoryService;
import com.peace.myblog.service.TagService;
import com.peace.myblog.utils.StringBothConvertLongArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-16 17:30
 */
@Controller
@RequestMapping("/admin")
public class BlogController {


    private static final String INPUT = "admin/blogs-input" ;
    private static final String LISTT = "admin/blogs" ;
    private static final String REDIRECT_LIST = "redirect:/admin/blogs" ;



    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogPage(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "3", value = "size") Integer size,
                           BlogSeach blog,
                           Model model) {
        blogMethod(pageNum, size, blog, model);
        model.addAttribute("category", categoryService.getAllCategory());
        return LISTT;
    }

    private void blogMethod(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "3", value = "size") Integer size,
                           BlogSeach blog,
                           Model model) {
        List<BlogModel> blogModels = new ArrayList<>();
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, size, orderBy);
        List<Blog> blogs = blogService.getAllBlog(blog);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);

        for (Blog blog1 : blogs) {

            BlogModel newBlogModel = new BlogModel();
            BeanUtils.copyProperties(blog1, newBlogModel);
            newBlogModel.setCategory(categoryService.getCategory(blog1.getCategoryId()));
            List<String> tagNames  = StringBothConvertLongArray.convertToStringList(blog1.getTagNames());
            newBlogModel.setTags(tagService.getTagByBlogTagName(tagNames));
            blogModels.add(newBlogModel);

        }

        model.addAttribute("blogPageInfo", blogPageInfo);
        model.addAttribute("blogModels", blogModels);
    }


    @PostMapping("/blogs/search")
    public String blogSearch(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "3", value = "size") Integer size,
                             BlogSeach blog,
                           Model model) {
        blogMethod(pageNum, size, blog, model);
        return "admin/blogs :: blogList";
    }


    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("blogModel", new BlogModel());
        model.addAttribute("tags", tagService.getAllTag());
        model.addAttribute("category", categoryService.getAllCategory());
        return INPUT;
    }


    @GetMapping("/blogs/{id}/edit")
    public String editBlog(@PathVariable Long id, Model model) {
        model.addAttribute("blogModel", blogService.getBlogModel(id));
        model.addAttribute("tags", tagService.getAllTag());
        model.addAttribute("category", categoryService.getAllCategory());
        return INPUT;
    }


    @PostMapping("/blogs")
    public String save(BlogModel blogModel, HttpSession httpSession,
                       RedirectAttributes attributes) {
        UserModel userModel = (UserModel) httpSession.getAttribute("userModel");
        blogModel.setAuthorName(userModel.getNickName());
        blogModel.setAuthorId(userModel.getId());
        Blog returnBlog = blogService.saveBlog(blogModel);
        if (returnBlog == null) {
            attributes.addFlashAttribute("message", "操作失败");

        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return REDIRECT_LIST;
    }


    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }
}
