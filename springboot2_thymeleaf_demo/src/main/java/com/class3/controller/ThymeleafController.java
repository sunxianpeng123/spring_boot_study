package com.class3.controller;

import com.class3.domain.Author;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/24
 * \* Time: 21:01
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
@RequestMapping
public class ThymeleafController {
    //  http://localhost:8080/index
    /**
     * 然后创建一个ThymeleafController用来映射HTTP请求与页面的跳转，下面写了两种方式，第一种比较直观和优雅，第二种相对普遍且代码较少，且迎合从struts2跳坑的朋友们…
     * Spring4.3以后为简化@RequestMapping(method = RequestMethod.XXX)的写法，故而将其做了一层包装，也就是现在的GetMapping、PostMapping、PutMapping、DeleteMapping、PatchMapping
     */
    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView view =new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("index");
// 设置属性
        view.addObject("title","the one page of mine");
        view.addObject("desc"," welcome to sun-web System");

        Author author=new Author();
        author.setAge(24);
        author.setName("sun");
        author.setEmail("294878304@qq.com");
        view.addObject("author",author);
        return view;
    }

    public String index1(HttpServletRequest request){
        // TODO 与上面的写法不同，但是结果一致。
        // 设置属性
        request.setAttribute("title","the one page of mine");
        request.setAttribute("desc","welcome to sun-web System");

        Author author=new Author();
        author.setAge(24);
        author.setName("sun");
        author.setEmail("294878304@qq.com");
        request.setAttribute("author",author);
        // 返回的 index 默认映射到 src/main/resources/templates/xxxx.html
        return "index";
    }
    /**
     * 最后在src/main/resources/templates目录下创建一个名index.html的模板文件，可以看到thymeleaf是通过在标签中添加额外属性动态绑定数据的
     */





}