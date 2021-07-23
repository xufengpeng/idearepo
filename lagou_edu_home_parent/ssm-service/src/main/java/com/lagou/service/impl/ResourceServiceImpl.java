package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.mapper.ResourceMapper;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        List<Resource> resourceByPage = resourceMapper.findAllResourceByPage(resourceVo);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceByPage);
        return pageInfo;
    }
}
