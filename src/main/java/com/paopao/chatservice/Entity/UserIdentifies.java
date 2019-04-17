package com.paopao.chatservice.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ：paopao
 * @date ：Created in 2019/4/9 15:57
 * @description：用户登录类型表
 */
@Entity
@Data
public class UserIdentifies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_identifies_id;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "users_foreign_key_id")
    private Users users;

    //登录类型(手机号、邮箱、用户名、第三方应用)
    @Column(nullable = false)
    private String identify_type;

    //标识(账号、手机号、邮箱、第三方的唯一标识)
    @Column(nullable = false)
    private String identify;

    //密码凭证
    @Column(nullable = false)
    private String credential;



}
