package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;
import com.lagou.mapper.UserMapper;
import com.lagou.service.UserService;
import com.lagou.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> users = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(Integer id ,String status) {
        User us=new User();
        us.setId(id);
        us.setStatus(status);
        us.setUpdate_time(new Date());
        userMapper.updateUserStatus(us);
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if(user1!=null && Md5.verify(user.getPassword(),"lagou",user1.getPassword()))
        {
            return user1;
        }else {
            return null;
        }

    }

    @Override
    public List<Role> findUserRelationRoleByUserId(Integer id) {
        List<Role> roleByUserId = userMapper.findUserRelationRoleByUserId(id);
        return roleByUserId;
    }

    @Override
    public void userRoleContext(UserVo userVo) {
        userMapper.deleteUserRoleContext(userVo.getUserId());
        List<Integer> roleIdList = userVo.getRoleIdList();
        for (Integer roleid : roleIdList) {
            User_Role_relation userRoleRelation=new User_Role_relation();
            Date date=new Date();
            userRoleRelation.setUserId(userVo.getUserId());
            userRoleRelation.setRoleId(roleid);
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);
            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedby("system");
            userMapper.saveUserRoleContext(userRoleRelation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        List<Role> roleList = userMapper.findUserRelationRoleByUserId(userId);
        List<Integer> roleIds=new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        List<Menu> menuList = userMapper.findParentMenuByRoleId(roleIds);
        for (Menu menu : menuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByParentId(menu.getId());
            menu.setSubMenuList(subMenuList);
        }
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        Map<String,Object>map=new HashMap<>();
        map.put("menuList",menuList);
        map.put("resourceList",resourceList);
        return new ResponseResult(true,200,"角色动态菜单成功",map);
    }
}
