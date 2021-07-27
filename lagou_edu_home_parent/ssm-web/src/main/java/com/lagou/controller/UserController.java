package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo<User> pageInfo = userService.findAllUserByPage(userVo);
        return new ResponseResult(true,200,"用户分页信息查询成功",pageInfo);
    }
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status="DISENABLE";
        }else {
            status="ENABLE";
        }

        userService.updateUserStatus(id,status);
        Map<String,String> map=new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"更新用户状态成功",map);
    }
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);
        if(login!=null){
            Map<String,Object> map=new HashMap<>();
            String access_token= UUID.randomUUID().toString();
            HttpSession session = request.getSession();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",login.getId());
            map.put("access_token",access_token);
            map.put("user_id",login.getId());
            map.put("user",login);
            System.out.println(access_token);
            return new ResponseResult(true,1,"登录成功",map);
        }else {
            return new ResponseResult(true,1,"用户名或密码错误",null);
        }
    }
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> userRole = userService.findUserRelationRoleByUserId(id);
        return new ResponseResult(true,200,"回显用户角色成功",userRole);
    }
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userRoleContext(userVo);
        return new ResponseResult(true,200,"角色分配成功",null);
    }
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        String header_token = request.getHeader("Authorization");
        String access_token = (String) request.getSession().getAttribute("access_token");
        if(header_token.equals(access_token)){
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        }else {
            return new ResponseResult(false,400,"获取用户权限失败",null);
        }
    }
}
