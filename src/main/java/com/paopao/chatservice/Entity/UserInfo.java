package com.paopao.chatservice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * @author ：paopao
 * @date ：Created in 2019/4/6 17:23
 * @description：用户表
 */

@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userInfoId;


    //用户账号
    @Column(nullable = false,unique = true)
    private String userAccount;

    //用户密码
    @Column(nullable = false)
    @JsonIgnore
    private String userPassword;



}
