package com.example.vueblog.controller;


import com.example.vueblog.common.lang.Result;
import com.example.vueblog.entity.User;
import com.example.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2023-07-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public  Object index(){
        User user = userService.getById(1);
        return Result.succ(200,"操作成功", user);
//        return userService.getById(1);
    }

    /**
     *  @RequestBody主要用来接受前端传递给后端的json字符串中数据（请求体数据）。
     *  前端使用POST提交（因为GET无请求体）
     *  @RequestBody和@RequestParam()可以同时使用，但是@RequestBody最多有一个而@RequestParam可以有多个
     *  @Validated注解用于检查user中填写的规则，如果不满足则抛出异常。可以在GlobalExceptionHandler中捕获此异常 进行自定义返回信息
     * @param user
     * @return
     */
    @PostMapping("/save")
    public  Result save(@Validated @RequestBody User user){

        return Result.succ(user);
    }


}
