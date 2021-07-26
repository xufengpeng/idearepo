package com.lagou.mapper;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);
    public void saveResource(Resource resource);
    public void updateResource(Resource resource);
}
