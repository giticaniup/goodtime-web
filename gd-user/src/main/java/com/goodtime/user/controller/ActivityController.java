package com.goodtime.user.controller;

import com.github.api.entity.Activity;
import com.github.api.service.ActivityService;
import com.goodtime.base.result.BaseResult;
import com.goodtime.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * ActivityController
 * Created by zhongcy on 2017-02-25.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class ActivityController extends BaseController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public Result addSystemMessage(@RequestBody Activity activity) {
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());
        activityService.saveActivity(activity);
        return SUCCESS;
    }

    @RequestMapping(value = "/activity/user/{userId}", method = RequestMethod.GET)
    public BaseResult<List<Activity>> listActivity(@PathVariable("userId") String userId) {
        checkParamNotBlank(userId, "用户ID不能为空");
        return new BaseResult<>(activityService.listActivity(userId));
    }

    @GetMapping(value = "activity/{id}")
    public BaseResult<Activity> getActivity(@PathVariable("id") String id){
        checkParamNotBlank(id,"活动id不能为空");
        return new BaseResult<>(activityService.getActivity(id));
    }
}
