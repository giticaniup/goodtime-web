package com.goodtime.user.controller;

import com.github.api.entity.User;
import com.github.api.entity.UserTask;
import com.github.api.service.UserInfoService;
import com.github.api.service.UserTaskService;
import com.goodtime.base.result.BaseResult;
import com.goodtime.user.enums.UserCodeEnums;
import com.goodtime.user.results.UserResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            mv.setViewName("userLogin");
        }else {
            User user = userInfoService.selectById(userId);
            mv.addObject("user", user);
            mv.setViewName("todoList");
        }
        return mv;
    }

    @RequestMapping("/getTask")
    @ResponseBody
    public List<UserTask> getUserTask(HttpSession session, String beginTime, String endTime) {
        //从session中获取当前用户信息
        Integer userId;
        if (session.getAttribute("userid") != null) {
            userId = (Integer) session.getAttribute("userid");
        } else {
            userId = 1;
        }
        return userTaskService.findTaskListByUserId(userId, beginTime, endTime);
    }

    @RequestMapping(value = "/addUserTask")
    @ResponseBody
    public BaseResult addUserTask(HttpSession session, UserTask userTask) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return new UserResult(UserCodeEnums.USER_NOTLOGIN);
        }
        userTask.setUserId(userId);
        try {
            userTask.checkParams();
            int result = userTaskService.insertUserTask(userTask);
            if (result != 1) {
                return new UserResult(UserCodeEnums.ADDUSER_ERROR);
            }
            return new BaseResult();
        } catch (IllegalArgumentException ie) {
            logger.error("addUserTask IllegalArgumentException,param={},ie={}", userTask, ie);
            return new UserResult(UserCodeEnums.PARAM_ILLEGAL);
        } catch (Exception e) {
            logger.error("addUserTask Exception,param={},info={}", userTask, e);
            return new UserResult(UserCodeEnums.SYSTEM_ERROR);
        }
    }
}
