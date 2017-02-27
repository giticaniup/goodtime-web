package com.goodtime.user.controller;

import com.github.api.entity.Activity;
import com.github.api.service.ActivityService;
import com.goodtime.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * ActivityController
 * Created by zhongcy on 2017-02-25.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class ActivityController extends BaseController{
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public Result addSystemMessage(@RequestBody Activity activity) {
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());
        activityService.saveActivity(activity);
        return SUCCESS;
    }
}
