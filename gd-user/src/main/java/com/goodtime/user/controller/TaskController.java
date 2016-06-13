package com.goodtime.user.controller;

import com.github.api.entity.User;
import com.github.api.entity.UserTask;
import com.github.api.service.UserInfoService;
import com.github.api.service.UserTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务控制层
 * Created by zhongcy on 2016/5/31.
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserTaskService userTaskService;

    @RequestMapping("/todoList")
    public ModelAndView todoList(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user = userInfoService.selectById(1);

        //省略登录功能，在此存储userId的值
        session.setAttribute("userId",user.getUserId());
        mv.addObject("user", user);
        mv.setViewName("todoList");
        return mv;
    }

    @RequestMapping("/getTask")
    @ResponseBody
    public List<UserTask> getUserTask(HttpSession session, String beginTime, String endTime) {
        //从session中获取当前用户信息
        Integer userId;
        if(session.getAttribute("userid")!=null){
            userId= (Integer) session.getAttribute("userid");
        }else {
            userId=1;
        }
        return userTaskService.findTaskListByUserId(userId, beginTime, endTime);
    }

    @RequestMapping(value = "/addUserTask",method = RequestMethod.POST)
    public int addUserTask(@RequestBody UserTask userTask){
        return userTaskService.insertUserTask(userTask);
    }
}