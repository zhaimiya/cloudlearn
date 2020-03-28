package com.cloud.order.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("user")
@RefreshScope
public class UserProperties {
    private String id;
    private String name;
    private Integer gender;
    private String phone;

    @Override
    public String toString() {
        return "UserConfig{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }
}
