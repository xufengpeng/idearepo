package com.lagou.mapper;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {
    public List<Menu> findAllMenuByPid(Integer pid);
    public List<Menu> findAllMenu();
    public Menu findMenuById(Integer id);
    public void saveMenu(Menu menu);
    public void updateMenu(Menu menu);
}
