package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    public List<ResourceCategory> findAllResourceCategory();
    public void saveResourceCategory(ResourceCategory resourceCategory);
    public void updateResourceCategory(ResourceCategory resourceCategory);
}
