package com.goodtime.user.controller;

import com.github.api.entity.User;
import com.github.api.entity.UserDiary;
import com.github.api.service.UserDiaryService;
import com.github.api.service.UserInfoService;
import com.goodtime.base.result.BaseResult;
import com.goodtime.base.result.Result;
import com.goodtime.user.utils.UserConstants;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 日志处理器
 * Created by zhongcy on 2016/6/27.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class DiaryController extends BaseController{

    private static final int PAGE_SIZE = 10;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserDiaryService userDiaryService;

    @RequestMapping(value = "/diary",method = RequestMethod.GET)
    @RequiresAuthentication
    public BaseResult getDiaryList(HttpSession session,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   @RequestParam (defaultValue = "1") int pageNum){
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        return new BaseResult<>(userDiaryService.findDiaryByUserId(userId, pageSize, pageNum));
    }

    @RequestMapping(value = "/diary",method = RequestMethod.POST)
    @RequiresAuthentication
    public Result saveDiary(HttpSession session,@RequestBody UserDiary userDiary){
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        userDiary.setUserId(userId);
        Date date = new Date();
        userDiary.setCreateTime(date);
        userDiary.setModifyTime(date);
        userDiaryService.saveUserDiary(userDiary);
        return SUCCESS;
    }

    @RequestMapping(value = "/diary/{id}",method = RequestMethod.PATCH)
    @RequiresAuthentication
    public Result updateDiary(HttpSession session,@RequestBody UserDiary userDiary, @PathVariable("id") Long id){
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        checkParamNotBlank(id,"日志id不能为空");
        userDiary.setUserId(userId);
        userDiary.setId(id);
        userDiary.setModifyTime(new Date());
        userDiaryService.updateUserDiary(userDiary);
        return SUCCESS;
    }

    @RequestMapping(value = "/diary",method = RequestMethod.DELETE)
    @RequiresAuthentication
    public Result deleteDiary(Long id){
        checkParamNotBlank(id,"日志id不能为空");
        userDiaryService.deleteUserDiary(id);
        return SUCCESS;
    }

    @RequestMapping(value = "/diary/{id}",method = RequestMethod.GET)
    @RequiresAuthentication
    public BaseResult getDiaryById(@PathVariable("id") Long id){
        UserDiary userDiary = userDiaryService.findDiaryById(id);
        return new BaseResult<>(userDiary);
    }

    @RequestMapping(value = "/diary/group/{id}", method = RequestMethod.GET)
    @RequiresAuthentication
    public BaseResult getDiaryByGroup(HttpSession session, @PathVariable("id") Integer id) {
        Integer userId = (Integer) session.getAttribute(UserConstants.CURRENT_USER);
        return new BaseResult<>(userDiaryService.getDiaryByGroup(userId,id));
    }

    //todo 整理旧代码
    @RequestMapping("/diaryList")
    public ModelAndView diaryList(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId != null) {
            User user = userInfoService.selectById(userId);
            mv.addObject("user", user);
            mv.setViewName("diary");
            return mv;
        } else {
            mv.setViewName("userLogin");
            return mv;
        }
    }

    @RequestMapping("/diaryList/{year}/{month}")
    public String diaryList(HttpSession session, @PathVariable("year") String year, @PathVariable("month") String
            month, Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId != null) {
            User user = userInfoService.selectById(userId);
            map.put("user", user);
            map.put("year", year);
            map.put("month", month);
            return "diary";
        } else {
            return "userLogin";
        }
    }

/*    @RequestMapping("/saveDiary")
    public String saveDiary(HttpSession session, UserDiary userDiary) {
        if (session.getAttribute("userId") == null) return "userLogin";
        userDiary.setUserId((Integer) session.getAttribute("userId"));
        userDiary.setGroupId(1);
        userDiaryService.saveUserDiary(userDiary);
        return "redirect:/diary/diaryList";
    }*/

    @RequestMapping("/")
    @ResponseBody
    public BaseResult getDiary(HttpSession session, int pageNum) {
        //从session中获取当前用户信息
        Integer userId = (Integer) session.getAttribute("userId");
        return new BaseResult<>(userDiaryService.findDiaryByUserId(userId, PAGE_SIZE, pageNum));
    }

    @RequestMapping("/{year}/{month}")
    @ResponseBody
    public BaseResult getDiary(HttpSession session, Integer pageNum, @PathVariable("year") Integer year, @PathVariable
            ("month") Integer month) {
        //从session中获取当前用户信息
        Integer userId = (Integer) session.getAttribute("userId");
        return new BaseResult<>(userDiaryService.findDiaryByDate(userId, PAGE_SIZE, pageNum, year, month));
    }
}
