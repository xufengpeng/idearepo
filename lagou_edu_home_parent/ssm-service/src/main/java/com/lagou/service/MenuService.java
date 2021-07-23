package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> findAllMenuByPid(Integer pid);
    public List<Menu> findAllMenu();
    public Menu findMenuById(Integer id);
    public void saveMenu(Menu menu);
    public void updateMenu(Menu menu);
}
