package com.goodtime.user.controller;

import com.github.api.entity.UserTask;
import com.github.api.service.UserTaskService;
import com.goodtime.base.result.BaseResult;
import com.goodtime.base.result.Result;
import com.goodtime.user.utils.UserConstants;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 任务控制层
 * Created by zhongcy on 2016/5/31.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class TaskController extends BaseController {

    @Autowired
    private UserTaskService userTaskService;


    @RequestMapping(value = "/task",method = RequestMethod.GET)
    @RequiresAuthentication
    public BaseResult<List<UserTask>> getUserTask(HttpSession session, String beginTime, String endTime) {
        //从session中获取当前用户信息
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        List<UserTask> userTasks = userTaskService.findTaskListByUserId(userId, beginTime, endTime);
        return new BaseResult<>(userTasks);
    }

    @RequestMapping(value = "/task",method = RequestMethod.POST)
    @RequiresAuthentication
    public Result addUserTask(HttpSession session, UserTask userTask) {
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        userTask.setUserId(userId);
        userTask.checkParams();
        userTaskService.insertUserTask(userTask);
        return SUCCESS;
    }
}
