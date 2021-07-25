package com.lagou.service.impl;

import com.lagou.domain.*;
import com.lagou.mapper.RoleMapper;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roles = roleMapper.findAllRole(role);
        return roles;
    }

    @Override
    public void saveRole(Role role) {
        Date date=new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }

    @Override
    public List<Integer> findMenuIdByRoleId(Integer roleId) {
        List<Integer> menuID = roleMapper.findMenuIdByRoleId(roleId);
        return menuID;
    }

    @Override
    public void roleMenuContext(RoleMenuVo roleMenuVo) {
        roleMapper.deleteRoleMenuContext(roleMenuVo.getRoleId());
        List<Integer> menuIdList = roleMenuVo.getMenuIdList();
        Date date=new Date();
        for (Integer menuId : menuIdList) {
            Role_menu_relation roleMenuRelation=new Role_menu_relation();
            roleMenuRelation.setRoleId(roleMenuVo.getRoleId());
            roleMenuRelation.setMenuId(menuId);
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");
            roleMapper.saveRoleMenuContext(roleMenuRelation);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleMapper.deleteRoleMenuContext(roleId);
        roleMapper.deleteRole(roleId);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        List<ResourceCategory> resourceListByRoleId = roleMapper.findResourceListByRoleId(roleId);
        return resourceListByRoleId;
    }

    @Override
    public void roleResourceContext(RoleResourceVo roleResourceVo) {
        roleMapper.deleteRoleResourceContext(roleResourceVo.getRoleId());
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();
        for (Integer resourceId : resourceIdList) {
            Date date=new Date();
            Role_resource_relation role_resource_relation=new Role_resource_relation();
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());
            role_resource_relation.setResourceId(resourceId);
            role_resource_relation.setCreatedTime(date);
            role_resource_relation.setUpdatedTime(date);
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("admin");
            roleMapper.saveRoleResourceContext(role_resource_relation);
        }
    }
}
