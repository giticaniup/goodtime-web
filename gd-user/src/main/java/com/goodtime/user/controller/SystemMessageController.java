package com.goodtime.user.controller;

import com.github.api.entity.SystemMessage;
import com.github.api.service.SystemMessageService;
import com.goodtime.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 系统消息Controller
 * Created by zhongcy on 16-12-5.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class SystemMessageController extends BaseController{

    @Autowired
    private SystemMessageService systemMessageService;

    @RequestMapping(value = "/sysMessage",method = RequestMethod.POST)
    public Result addSystemMessage(@RequestBody SystemMessage systemMessage){
        systemMessage.setCreateTime(new Date());
        systemMessage.setModifyTime(new Date());
        systemMessageService.addSystemMessage(systemMessage);
        return SUCCESS;
    }
}
