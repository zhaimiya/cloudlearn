package com.cloud.user.srv.impl;

import com.cloud.user.dataobject.pojo.UserInfo;
import com.cloud.user.repository.UserRepository;
import com.cloud.user.srv.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSrvImpl implements UserSrv {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo findByOpenid(String id) {
        return userRepository.findByOpenid(id);
    }
}
