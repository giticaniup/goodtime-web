package com.goodtime.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 任务控制层
 * Created by zhongcy on 2016/5/31.
 */
@Controller
@RequestMapping("task")
public class TaskController {
    @RequestMapping("/todoList")
    public String todoList() {
        return "todoList";
    }
}
