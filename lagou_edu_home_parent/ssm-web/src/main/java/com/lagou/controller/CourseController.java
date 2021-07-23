package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseTeacherVo;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){
        List<Course> courses = courseService.findCourseByCondition(courseVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courses);
        return responseResult;
    }
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));
        String originalFilename = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String uploadPath=substring+"upload\\";
        File filePath=new File(uploadPath,newFileName);
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:"+filePath);
        }
        file.transferTo(filePath);
        Map<String,String> map=new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200, "上传图片成功", map);
        return responseResult;
    }
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseTeacherVo courseTeacherVo) throws InvocationTargetException, IllegalAccessException {
        if(courseTeacherVo.getId()==null){
            courseService.saveCourseOrTeacher(courseTeacherVo);
            ResponseResult responseResult=new ResponseResult(true,200,"新增课程成功",null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseTeacherVo);
            ResponseResult responseResult=new ResponseResult(true,200,"修改课程成功",null);
            return responseResult;
        }

    }
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseTeacherVo courseById = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据ID查询课程信息成功", courseById);
        return responseResult;
    }
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatus(id,status);
        Map<String,Integer> map=new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "更新课程状态成功", map);
        return responseResult;
    }
}
