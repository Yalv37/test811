package cuz.gpa.controller;

import com.google.gson.Gson;
import cuz.gpa.GpaApplication;
import cuz.gpa.mapper.StudentMapper;
import cuz.gpa.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
public class StudentController {
    @Autowired
    StudentMapper studentMapper;
    Gson gson=new Gson();

    @GetMapping("/student")
    public String grtStudentList(){
        List<Student> studentList = studentMapper.selectList(null);
        return  gson.toJson(studentList);
    }

    @PostMapping("/addstudent")
    public void AddStudent(@RequestBody Student student){
        studentMapper.insert(student);
    }
    @DeleteMapping("/delstudent")
    public void DeleteStudent(@RequestBody Student student) {
        studentMapper.deleteById(student);
    }

    @PutMapping("/updatestudent")
    public void UpdateStudent(@RequestBody Student student) {
        studentMapper.updateById(student);
    }



}
