package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> category = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"查询菜单分类成功",category);
    }
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
    if(resourceCategory.getId()==null){
        resourceCategoryService.saveResourceCategory(resourceCategory);
        return new ResponseResult(true,200,"新增资源分类信息成功",null);
    }else {
        resourceCategoryService.updateResourceCategory(resourceCategory);
        return new ResponseResult(true,200,"更新资源分类信息成功",null);
    }
    }
}
