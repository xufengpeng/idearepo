package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.mapper.MenuMapper;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findAllMenuByPid(Integer pid) {
        List<Menu> allMenu = menuMapper.findAllMenuByPid(pid);
        return allMenu;
    }

    @Override
    public PageInfo<Menu> findAllMenu(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Menu> allMenu = menuMapper.findAllMenu();
        PageInfo<Menu> pageInfo = new PageInfo<>(allMenu);
        return pageInfo;
    }

    @Override
    public Menu findMenuById(Integer id) {
        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }

    @Override
    public void saveMenu(Menu menu) {
        Date date=new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("manager");
        menuMapper.updateMenu(menu);
    }
}
