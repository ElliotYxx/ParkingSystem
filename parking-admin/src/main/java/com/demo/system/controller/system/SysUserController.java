package com.demo.system.controller.system;

import com.demo.common.res.ResultResponse;
import com.demo.common.utils.Constants;
import com.demo.common.utils.JwtUtil;
import com.demo.system.entity.User;
import com.demo.system.service.IUserService;
import com.demo.system.vo.VoPage;
import com.demo.system.vo.VoUser;
import com.demo.system.vo.VoUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Author sheva
 * @create 2021/2/24 13:36
 */
@Slf4j
@RestController
public class SysUserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/info")
    public ResultResponse userInfo(@RequestParam("token") String token){
        ResultResponse res = new ResultResponse();
        // 验证 token 的合法姓  如果成功则返回 success:username（自定义规则）
        String tokenValue = JwtUtil.verifyToken(token);
        if (tokenValue != null && tokenValue.startsWith(JwtUtil.TOKEN_SUCCESS)){
            log.info("token合法...");
            // 如果合法 返回需要的用户信息
            String username = tokenValue.replaceFirst(JwtUtil.TOKEN_SUCCESS, "");
            User user = userService.findByUsername(username);
            String roleName = "";
            if (user.getRoleId().equals(Constants.ADMIN_ID)){
                roleName = "admin";
            }else{
                roleName = "user";
            }
            // 封装用户信息
            VoUserInfo userInfo = new VoUserInfo();
            userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            userInfo.setIntroduction(roleName + "测试");
            userInfo.setName(username);
            List<String> roles = Arrays.asList(roleName);
            userInfo.setRoles(roles);
            log.info("用户信息: " + userInfo.toString());

            res.setData(userInfo);
            res.setMessage(Constants.MESSAGE_OK);
            res.setCode(Constants.STATUS_OK);
        }else{
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL + " 获取用户信息失败...");
            res.setData("fail");
        }
        return res;
    }

    @GetMapping("/user/list")
    public ResultResponse userList(@RequestParam("page") int pageNo,
                                   @RequestParam("limit") int limit){
        ResultResponse res = null;
        //log.info("page: " + pageNo + "limit: " + limit + "idSort: " + idSort);
        VoPage page = this.userService.selectUsers(pageNo - 1, limit);
        log.info("查询到的分页信息：" + page.toString());
        res = new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, page);
        return res;

    }
}
