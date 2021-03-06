package com.lagou.mapper;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;
import com.lagou.domain.Role_resource_relation;

import java.util.List;

public interface RoleMapper {
    public List<Role> findAllRole(Role role);
    public void saveRole(Role role);
    public void updateRole(Role role);
    public List<Integer> findMenuIdByRoleId(Integer roleId);

    public void deleteRoleMenuContext(Integer roleId);
    public void saveRoleMenuContext(Role_menu_relation roleMenuRelation);

    public void deleteRole(Integer id);
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);

    public void deleteRoleResourceContext(Integer roleId);
    public void saveRoleResourceContext(Role_resource_relation role_resource_relation);
}
