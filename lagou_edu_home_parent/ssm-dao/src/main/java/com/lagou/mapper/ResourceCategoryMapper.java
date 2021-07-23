package com.lagou.mapper;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    public List<ResourceCategory> findAllResourceCategory();
    public void saveResourceCategory(ResourceCategory resourceCategory);
    public void updateResourceCategory(ResourceCategory resourceCategory);
}
