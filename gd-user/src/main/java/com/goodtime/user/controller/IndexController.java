package com.goodtime.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 主页跳转
 * Created by zhongcy on 2016/6/27.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/task/todoList";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/task/todoList";
    }

    @RequestMapping("/index/testMap")
    public String testMap(HttpServletRequest request,@RequestParam("id") String id,  @RequestParam() Map<String,
            String> map1, Map<String,Object> map) {
        System.out.println("id:"+id);
        System.out.println(request.getParameter("id"));
        try {
            System.out.println(request.getInputStream().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("map1:" + map1);
        map1.put("test", "test1024");
        map.put("test", "test1024");
        System.out.println("map1:" + map1);
        System.out.println("map:"+map);
        return "test";
    }

    @ResponseBody
    @RequestMapping("/index/testJsonMap")
    public String testJsonMap(@RequestBody Map<String,String> map1){
        System.out.println(map1);
        return "success";
    }
}
