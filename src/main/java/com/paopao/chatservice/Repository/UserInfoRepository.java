package com.paopao.chatservice.Repository;

import com.paopao.chatservice.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into user_info(user_account,user_password) value(?1,?2)",nativeQuery = true)
    int addUser(String user_account,String user_password);

    UserInfo findByUserAccountAndUserPassword(String user_account,String user_password);

}
