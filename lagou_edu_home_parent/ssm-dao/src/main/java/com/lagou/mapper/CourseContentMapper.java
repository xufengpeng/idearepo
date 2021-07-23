package com.lagou.mapper;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);
    public Course findCourseByCourseId(Integer courseId);
    public void saveSection(CourseSection courseSection);
    public void updateSection(CourseSection courseSection);
    public void updateSectionStatus(CourseSection courseSection);

}
