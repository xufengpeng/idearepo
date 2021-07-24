package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseTeacherVo;
import com.lagou.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    public List<Course> findCourseByCondition(CourseVo courseVo);
    public void saveCourseOrTeacher(CourseTeacherVo courseTeacherVo) throws InvocationTargetException, IllegalAccessException;
    public CourseTeacherVo findCourseById(Integer id);
    public void updateCourseOrTeacher(CourseTeacherVo courseTeacherVo) throws InvocationTargetException, IllegalAccessException;
    public void updateCourseStatus(Integer id,Integer status);

}
