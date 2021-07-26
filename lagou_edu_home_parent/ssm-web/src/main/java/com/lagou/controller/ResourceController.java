package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> resourcePageInfo = resourceService.findAllResourceByPage(resourceVo);
        return new ResponseResult(true,200,"查询资源分页成功",resourcePageInfo);
    }
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if(resource.getId()==null){
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"新增资源信息成功",null);
        }
        else {
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"更新资源信息成功",null);
        }
    }
}
