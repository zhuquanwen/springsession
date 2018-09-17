package com.zqw.springsession.model;

import lombok.Data;

import java.security.Principal;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2018/9/17 15:42
 * @since jdk1.8
 */
@Data
public class User implements Principal {
    private String username;
    private String password;
    @Override
    public String getName() {
        return username;
    }
}
