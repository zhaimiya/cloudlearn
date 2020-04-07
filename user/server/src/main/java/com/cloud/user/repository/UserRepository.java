package com.cloud.user.repository;

import com.cloud.user.dataobject.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,String> {
    UserInfo findByOpenid(String openid);
}
