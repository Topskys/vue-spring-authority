package com.manong.controller.admin;

import com.manong.utils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/education")
public class LoginController {

    /**
     * 用户登录
     * @return
     */
    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @GetMapping("/info")
    public Result info(){
        return Result.ok().data("roles","[SUPER_ADMIN]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    /**
     * 用户退出
     * @return
     */
    @GetMapping("/logout")
    public Result logout(){
        return Result.ok();
    }

}
