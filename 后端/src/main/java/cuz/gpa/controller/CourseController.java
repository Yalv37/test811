package cuz.gpa.controller;

import com.google.gson.Gson;
import cuz.gpa.mapper.CourseMapper;
import cuz.gpa.model.Course;
import cuz.gpa.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private  CourseMapper courseMapper;
    private Gson gson = new Gson();

    @GetMapping("/course")
    public String getCourseList(){
        List<Course> courseList = courseMapper.selectList(null);
        return  gson.toJson(courseList);
    }
    @PostMapping("/addcourse")
    public void AddCourse(@RequestBody Course course){
        courseMapper.insert(course);
    }
    @DeleteMapping("/delcourse")
    public void DeleteCourse(@RequestBody Course course) {
        courseMapper.deleteById(course);
    }

    @PutMapping("/updatecourse")
    public void UpdateStudent(@RequestBody Course course) {
        courseMapper.updateById(course);
    }
}
