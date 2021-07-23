package com.lagou.service.impl;

import com.lagou.domain.Course;
import com.lagou.domain.CourseTeacherVo;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.mapper.CourseMapper;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        return courseMapper.findCourseByCondition(courseVo);
    }

    @Override
    public void saveCourseOrTeacher(CourseTeacherVo courseTeacherVo) throws InvocationTargetException, IllegalAccessException {
        Course course=new Course();
        BeanUtils.copyProperties(course,courseTeacherVo);
        Date date=new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        course.setIsDel(0);
        courseMapper.saveCourse(course);
        int id = course.getId();
        Teacher teacher=new Teacher();
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        BeanUtils.copyProperties(teacher,courseTeacherVo);
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseTeacherVo findCourseById(Integer id) {
        CourseTeacherVo courseById = courseMapper.findCourseById(id);
        return courseById;
    }

    @Override
    public void updateCourseOrTeacher(CourseTeacherVo courseTeacherVo) throws InvocationTargetException, IllegalAccessException {
        Course course=new Course();
        BeanUtils.copyProperties(course,courseTeacherVo);
        Date date=new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourse(course);
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacher,courseTeacherVo);
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer id, Integer status) {
        Course course=new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);
    }
}
