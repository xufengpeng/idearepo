package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    public List<Role> findAllRole(Role role);
    public void saveRole(Role role);
    public void updateRole(Role role);
    public List<Integer> findMenuIdByRoleId(Integer roleId);
    public void roleMenuContext(RoleMenuVo roleMenuVo);
    public void deleteRole(Integer roleId);
}
