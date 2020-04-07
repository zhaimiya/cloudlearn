package com.cloud.user.srv;

import com.cloud.user.dataobject.pojo.UserInfo;

public interface UserSrv {
    UserInfo findByOpenid(String id);
}
