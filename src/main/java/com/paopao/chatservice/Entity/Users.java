package com.paopao.chatservice.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author ：paopao
 * @date ：Created in 2019/4/9 15:55
 * @description：用户基本信息表
 */
@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long users_id;

    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<UserIdentifies> userIdentifiesSet;

    //用户昵称
    private String nickname;

    //用户性别
    @Column(nullable = false, columnDefinition = "enum('男','女')")
    private String sex;

    //用户头像
    private String head_picture;


}
