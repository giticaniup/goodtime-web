package com.goodtime.user.results;

import com.goodtime.base.result.BaseResult;
import com.goodtime.user.enums.UserCodeEnums;

/**
 * 用户结果
 * Created by zhongcy on 2016/6/15.
 */
public class UserResult extends BaseResult{

    private static final long serialVersionUID = 203870306075207634L;

    public UserResult(UserCodeEnums enums) {
        this.setErrorCode(enums.getErrorCode());
        this.setErrorMsg(enums.getErrorMsg());
    }
}
