package com.goodtime.user.controller;

import com.github.api.entity.User;
import com.github.api.entity.UserDiary;
import com.github.api.service.UserDiaryService;
import com.github.api.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 日志处理器
 * Created by zhongcy on 2016/6/27.
 */
@Controller
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserDiaryService userDiaryService;

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

    @RequestMapping("/saveDiary")
    public String saveDiary(HttpSession session, UserDiary userDiary) {
        if (session.getAttribute("userId") == null) return "userLogin";
        userDiary.setUserId((Integer) session.getAttribute("userId"));
        userDiary.setGroupId(1);
        userDiaryService.saveUserDiary(userDiary);
        return "redirect:/diary/diaryList";
    }

    @RequestMapping("/")
    @ResponseBody
    public List<UserDiary> getDiary(HttpSession session, int pageNum) {
        //从session中获取当前用户信息
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId != null) {
            int PAGE_SIZE = 10;
            return userDiaryService.findDiaryByUserId(userId, PAGE_SIZE, pageNum);
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("/{year}/{month}")
    public String getDiaryByDate(@PathVariable("year") int year, @PathVariable("month") int month,Map<String,Integer> map){
        map.put("year",year);
        map.put("month",month);
        return "userDiary";
    }
}
