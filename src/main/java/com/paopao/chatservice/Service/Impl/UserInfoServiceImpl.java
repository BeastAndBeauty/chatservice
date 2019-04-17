package com.paopao.chatservice.Service.Impl;

import com.paopao.chatservice.Repository.UserInfoRepository;
import com.paopao.chatservice.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：paopao
 * @date ：Created in 2019/4/6 20:05
 * @description：
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public String userLogin(String user_account, String user_password) {
        if (userInfoRepository.findByUserAccountAndUserPassword(user_account,user_password)!=null)
            return "S";
        return "F";
    }

    @Override
    public String userRegister(String user_account, String user_password) {
        if (userInfoRepository.addUser(user_account,user_password)==1)
            return "S";
        return "F";
    }
}
