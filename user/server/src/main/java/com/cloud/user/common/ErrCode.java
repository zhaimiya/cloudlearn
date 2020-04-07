package com.cloud.user.common;

import javax.persistence.criteria.CriteriaBuilder;

public final class ErrCode {

    public static final Integer ERROR_LOGIN_CODE = 30001;
    public static final String ERROR_LOGIN_MSG = "用户不存在";

    public static final Integer ERROR_ROLE_CODE = 30002;
    public static final String ERROR_ROLE_MSG = "权限有误";

    public static final Integer ERROR_PARAM_CODE = 30003;
    public static final String ERROR_PARAM_MSG = "异常登录";
}
