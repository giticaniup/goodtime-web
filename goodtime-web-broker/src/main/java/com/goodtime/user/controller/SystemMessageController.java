package com.goodtime.user.controller;

import com.github.api.entity.SystemMessage;
import com.github.api.service.SystemMessageService;
import com.goodtime.base.result.BaseResult;
import com.goodtime.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 系统消息Controller
 * Created by zhongcy on 16-12-5.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class SystemMessageController extends BaseController {

    @Autowired
    private SystemMessageService systemMessageService;

    @RequestMapping(value = "/sysMessage", method = RequestMethod.POST)
    public Result addSystemMessage(@RequestBody SystemMessage systemMessage) {
        systemMessage.setCreateTime(new Date());
        systemMessage.setModifyTime(new Date());
        systemMessageService.addSystemMessage(systemMessage);
        return SUCCESS;
    }

    @RequestMapping(value = "/sysMessage", method = RequestMethod.GET)
    public BaseResult<List<SystemMessage>> getSystemMessage() {
        return new BaseResult<>(systemMessageService.getSystemMessage());
    }

    @RequestMapping(value = "/sysMessage/{id}", method = RequestMethod.PATCH)
    public Result markSystemMessage(@PathVariable("id") Integer id) {
        systemMessageService.markSystemMessage(id);
        return SUCCESS;
    }

    @RequestMapping(value = "/sysMessage/count",method = RequestMethod.GET)
    public BaseResult<Long> getSysMessageCount(){
        return new BaseResult<>(systemMessageService.findCount());
    }

}
