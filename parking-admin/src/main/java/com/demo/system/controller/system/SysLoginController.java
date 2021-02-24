package com.demo.system.controller.system;

import com.demo.common.res.ResultResponse;
import com.demo.common.utils.Constants;
import com.demo.common.utils.JwtUtil;
import com.demo.system.service.IUserService;
import com.demo.system.vo.VoToken;
import com.demo.system.vo.VoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author sheva
 * @create 2021/2/24 10:59
 */
@Slf4j
@RestController
public class SysLoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user/login")
    public ResultResponse login(@RequestBody VoUser user){
        ResultResponse res = new ResultResponse();
        try{
            VoUser u = this.userService.checkLogin(user);
            if (null != u){
                log.info("登陆成功...");
                String token = JwtUtil.createToken("admin", "1");
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(new VoToken(token));
            }else{
                log.info("登陆失败...");
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL + "用户名和密码不匹配...");
                res.setData("fail");
            }
        }catch (Exception e){
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL + e.getMessage());
            res.setData("fail");
        }
        return res;
    }

    @PostMapping("/user/logout")
    public ResultResponse logout(@RequestHeader("X-TOKEN") String token){
        ResultResponse res = new ResultResponse();
        // 验证 token 和合法性与有效性
        String tokenValue = JwtUtil.verifyToken(token);
        // 获取 token 中的用户名
        String username = tokenValue.replaceFirst(JwtUtil.TOKEN_SUCCESS, "");
        // 移除 session 中的登陆标记
        res.setMessage("logout success");
        res.setData("logout success");
        res.setCode(Constants.STATUS_OK);
        return res;

    }
}
