package com.lagou.mapper;

import com.lagou.domain.Test;

import java.util.List;

public interface TestMapper {
    public List<Test> findAllTest();
    public void save();
    public void add();
    public void update();
    public void dev();
    public void success();
}
