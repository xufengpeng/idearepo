package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> roles = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询角色成功",roles);
    }
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if(role.getId()==null){
            roleService.saveRole(role);
            return new ResponseResult(true,200,"新增角色成功",null);
        }else {
            roleService.updateRole(role);
            return new ResponseResult(true,200,"更新角色成功",null);
        }
    }
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenuByPid(-1);
        Map<String,List> map=new HashMap<>();
        map.put("parentMenuList",allMenu);
        return new ResponseResult(true,200,"查询所有菜单成功",map);
    }
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuId = roleService.findMenuIdByRoleId(roleId);
        return new ResponseResult(true,200,"回显菜单ID成功",menuId);
    }
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleMenuContext(roleMenuVo);
        return new ResponseResult(true,200,"角色分配菜单成功",null);
    }
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功",null);
    }
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<ResourceCategory> resourceListByRoleId = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true,200,"查询角色相关资源信息成功",resourceListByRoleId);

    }
}
