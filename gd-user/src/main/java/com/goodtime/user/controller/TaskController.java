package com.goodtime.user.controller;

import com.github.api.entity.User;
import com.github.api.entity.UserTask;
import com.github.api.service.UserInfoService;
import com.github.api.service.UserTaskService;
import com.goodtime.base.result.Result;
import com.goodtime.user.utils.UserConstants;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 任务控制层
 * Created by zhongcy on 2016/5/31.
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserTaskService userTaskService;

    @RequestMapping("/todoList")
    @RequiresAuthentication
    public ModelAndView todoList(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user = userInfoService.selectById((Integer) session.getAttribute(UserConstants.CURRENT_USER));
        mv.addObject("user", user);
        mv.setViewName("todoList");
        return mv;
    }

    @RequestMapping("/getTask")
    @RequiresAuthentication
    public List<UserTask> getUserTask(HttpSession session, String beginTime, String endTime) {
        //从session中获取当前用户信息
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        return userTaskService.findTaskListByUserId(userId, beginTime, endTime);
    }

    @RequestMapping(value = "/addUserTask")
    @RequiresAuthentication
    public Result addUserTask(HttpSession session, UserTask userTask) {
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        userTask.setUserId(userId);
        userTask.checkParams();
        userTaskService.insertUserTask(userTask);
        return SUCCESS;
    }
}
