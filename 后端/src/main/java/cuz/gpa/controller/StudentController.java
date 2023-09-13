package cuz.gpa.controller;

import com.google.gson.Gson;
import cuz.gpa.GpaApplication;
import cuz.gpa.mapper.StudentMapper;
import cuz.gpa.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
public class StudentController {
    @Autowired
    StudentMapper studentMapper;
    Gson gson=new Gson();

    @GetMapping("/student")
    public String getStudentList(){
        List<Student> studentList = studentMapper.selectList(null);
        return  gson.toJson(studentList);
    }
    @GetMapping("/student1")
    public String getStudent1List(){
        List<Student> studentList = studentMapper.selectList(null);
        List<Student> studentList1 = new ArrayList<>();
        for(Student s:studentList){
            if(s.getGpa()>=3.5){
                studentList1.add(s);
            }
        }

        return  gson.toJson(studentList1);
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
