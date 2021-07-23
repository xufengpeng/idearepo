package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {
    public PageInfo<User> findAllUserByPage(UserVo userVo);
    public void updateUserStatus(Integer id ,String status);
    public User login(User user) throws Exception;
    public List<Role> findUserRelationRoleByUserId(Integer id);
    public void userRoleContext(UserVo userVo);
    public ResponseResult getUserPermissions(Integer userId);
}
