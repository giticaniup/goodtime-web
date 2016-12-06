package com.goodtime.user.controller;

import com.github.api.entity.DiaryGroup;
import com.github.api.service.DiaryGroupService;
import com.goodtime.base.result.BaseResult;
import com.goodtime.base.result.Result;
import com.goodtime.user.utils.UserConstants;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 日志分类Controller
 * Created by zhongcy on 2016/11/30.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class DiaryGroupController extends BaseController {

    @Autowired
    private DiaryGroupService diaryGroupService;

    @RequestMapping(value = "/diaryGroup", method = RequestMethod.GET)
    @RequiresAuthentication
    public BaseResult getDiaryGroup(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        return new BaseResult<>(diaryGroupService.getDiaryGroup(userId));
    }

    @RequestMapping(value = "/diaryGroup", method = RequestMethod.POST)
    @RequiresAuthentication
    public Result saveDiaryGroup(HttpSession session, @RequestBody DiaryGroup diaryGroup) {
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        diaryGroup.setCreator(userId);
        diaryGroupService.addDiaryGroup(diaryGroup);
        return SUCCESS;
    }

    @RequestMapping(value = "/diaryGroup", method = RequestMethod.PATCH)
    @RequiresAuthentication
    public Result updateDiaryGroup(@RequestBody DiaryGroup diaryGroup) {
        diaryGroupService.updateDiaryGroup(diaryGroup);
        return SUCCESS;
    }

    @RequestMapping(value = "/diaryGroup", method = RequestMethod.DELETE)
    @RequiresAuthentication
    public Result deleteDiaryGroup(Integer id) {
        diaryGroupService.deleteDiaryGroup(id);
        return SUCCESS;
    }
}
