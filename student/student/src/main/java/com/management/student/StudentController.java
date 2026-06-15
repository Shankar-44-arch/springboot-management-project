package com.management.student;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class StudentController {

    private StudentService studentService;

    @GetMapping("/std")
    public List<Student> std(){
        return studentService.getAllStd();
    }

    @PostMapping("/std")
    public String addStd(@RequestBody Student std){
        return studentService.addStd(std);
    }
}
