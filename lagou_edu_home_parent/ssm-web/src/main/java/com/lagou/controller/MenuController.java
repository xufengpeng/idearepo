package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true,200,"查询所有菜单成功",allMenu);
    }
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        List<Menu> parentMenu = menuService.findAllMenuByPid(-1);
        Map<String,Object> map=new HashMap<>();
        if(id==-1){
            map.put("menuInfo",null);
            map.put("parentMenuList",parentMenu);
            return new ResponseResult(true,200,"回显菜单信息成功",map);
        }else {
            Menu menu = menuService.findMenuById(id);
            map.put("menuInfo",menu);
            map.put("parentMenuList",parentMenu);
            return new ResponseResult(true,200,"回显菜单信息成功",map);
        }
    }
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if(menu.getId()==null){
            menuService.saveMenu(menu);
            return new ResponseResult(true,200,"添加菜单成功",null);
        }else {
            menuService.updateMenu(menu);
            return new ResponseResult(true,200,"更新菜单成功",null);
        }
    }
}
