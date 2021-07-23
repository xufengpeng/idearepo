package com.lagou.mapper;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    public List<User> findAllUserByPage(UserVo userVo);
    public void updateUserStatus(User user);
    public User login(User user);
    public void deleteUserRoleContext(Integer userId);
    public void saveUserRoleContext(User_Role_relation userRoleRelation);
    public List<Role> findUserRelationRoleByUserId(Integer id);
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    public List<Menu> findSubMenuByParentId(Integer pid);
    public List<Resource> findResourceByRoleId(List<Integer> ids);
}
