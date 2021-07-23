package com.lagou.service.impl;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
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
}
