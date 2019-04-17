package com.paopao.chatservice.Service;

public interface UserInfoService {

    String userLogin(String user_account,String password);

    String userRegister(String userAccount,String password);

}
