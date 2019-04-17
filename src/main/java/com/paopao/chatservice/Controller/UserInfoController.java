package com.paopao.chatservice.Controller;

import com.paopao.chatservice.Service.UserInfoService;
import com.paopao.chatservice.Util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/4/6 20:53
 * @description：
 */

@RestController
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    @PostMapping(value = "/UserLogin.do")
    public Map UserLogin(@RequestBody Map<String,String> map){
        if (userInfoService.userLogin(map.get("UserAccount"),map.get("UserPassword")).equals("S"))
            return ResultUtil.success("登录成功");
        return ResultUtil.fail("账号或密码错误");
    }

    @PostMapping(value = "/UserRegister.do")
    public Map UserRegister(@RequestBody Map<String, String> map) {
        if (userInfoService.userRegister(map.get("UserAccount"), map.get("UserPassword")).equals("S"))
            return ResultUtil.success("注册成功");
        return ResultUtil.fail("注册失败");
    }


}
