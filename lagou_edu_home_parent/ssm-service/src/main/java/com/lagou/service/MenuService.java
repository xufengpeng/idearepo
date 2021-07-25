package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> findAllMenuByPid(Integer pid);
    public PageInfo<Menu> findAllMenu(Integer currentPage,Integer pageSize);
    public Menu findMenuById(Integer id);
    public void saveMenu(Menu menu);
    public void updateMenu(Menu menu);
}
