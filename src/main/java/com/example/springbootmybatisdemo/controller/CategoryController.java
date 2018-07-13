package com.example.springbootmybatisdemo.controller;

import com.example.springbootmybatisdemo.mapper.CategoryMapper;
import com.example.springbootmybatisdemo.pojo.Category;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    @RequestMapping("/listCategory")
    public String listCategory(Model model) throws Exception{
        List<Category> categories=categoryMapper.findAll();
        model.addAttribute("category",categories);
        System.out.println();
        return "listCategory";
    }

    @RequestMapping("/listCategories")
    public String listCategories(Model model,
                                 @RequestParam(value = "start",defaultValue = "0")int start,
                                 @RequestParam(value = "size",defaultValue = "5") int size) throws Exception{
        PageHelper.startPage(start,size,"id desc");
        List<Category> categories=categoryMapper.findAll();
        PageInfo<Category> pageInfo=new PageInfo<>(categories);
        System.out.println(pageInfo.getPageNum());
        model.addAttribute("pageInfo",pageInfo);
        return "listCategories";
    }

    @RequestMapping("/addCategory")
    public String addCategory(Category category) throws Exception{
        categoryMapper.save(category);
        return "redirect:listCategories";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category category)throws Exception{
        categoryMapper.delete(category.getId());
        return "redirect:listCategories";
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(Category category)throws Exception{
        categoryMapper.update(category);
        return "redirect:listCategories";
    }
    @RequestMapping("/editCategory")
    public String editCategory(int id,Model model)throws Exception{
        Category category=categoryMapper.get(id);
        model.addAttribute("category",category);
        return "editCategory";
    }


}
