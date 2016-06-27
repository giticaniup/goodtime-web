package com.goodtime.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 主页跳转
 * Created by zhongcy on 2016/6/27.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "redirect:/task/todoList";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userId");
        return "redirect:/task/todoList";
    }
}
