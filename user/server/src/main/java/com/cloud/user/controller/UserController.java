package com.cloud.user.controller;

import com.cloud.user.common.Constant;
import com.cloud.user.common.ErrCode;
import com.cloud.user.dataobject.pojo.UserInfo;
import com.cloud.user.exception.LoginException;
import com.cloud.user.srv.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserSrv userSrv;

    @RequestMapping("/buyer")
    public String buyerLogin(@RequestParam(Constant.LOGIN_PARAM_OPENID) String openid,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        UserInfo userInfo = userSrv.findByOpenid(openid);
        if (null == userInfo) {
            throw new LoginException(ErrCode.ERROR_LOGIN_MSG, ErrCode.ERROR_LOGIN_CODE);
        }
        if (!Constant.USER_ROLE_BUYER.equals(userInfo.getRole())) {
            throw new LoginException(ErrCode.ERROR_PARAM_MSG, ErrCode.ERROR_PARAM_CODE);
        }
        Cookie cookie = new Cookie(Constant.LOGIN_PARAM_OPENID,openid);
        cookie.setMaxAge(120);
        response.addCookie(cookie);
        return "OK";
    }

    @RequestMapping("/seller")
    public String sellerLogin(@RequestParam(Constant.LOGIN_PARAM_TOKEN) String openid,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies){
            if(Constant.LOGIN_PARAM_TOKEN.equals(cookie.getName())&&!StringUtils.isEmpty(cookie.getName())){
                return "OK";
            }
        }


        UserInfo userInfo = userSrv.findByOpenid(openid);
        if (null == userInfo) {
            throw new LoginException(ErrCode.ERROR_LOGIN_MSG, ErrCode.ERROR_LOGIN_CODE);
        }
        if (!Constant.USER_ROLE_SELLER.equals(userInfo.getRole())) {
            throw new LoginException(ErrCode.ERROR_ROLE_MSG, ErrCode.ERROR_ROLE_CODE);
        }

        String uuid = UUID.randomUUID().toString().replaceAll("_","");

        Cookie cookie = new Cookie(Constant.LOGIN_PARAM_TOKEN,uuid);
        cookie.setMaxAge(120);
        response.addCookie(cookie);
        return  "OK";
    }


}
