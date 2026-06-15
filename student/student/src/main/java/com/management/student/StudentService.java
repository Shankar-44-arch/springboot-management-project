package com.management.student;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    List<Student> stdList = new ArrayList<>();

    public StudentService(){
        stdList.add(new Student(1L, "Sam", "Gerald"));
        stdList.add(new Student(1L, "Sarath", "B"));
    }

    public List<Student> getAllStd() {
        return stdList;
    }

    public String addStd(Student std){
        for (Student stdItm : stdList){
            if(stdItm.getStdId().equals(std.getStdId())){
                return "Student " + std.getFirstName() + " " + std.getLastName() +
                        "had already exists";
            }
        }
        stdList.add(std);
        return "Student " + std.getFirstName() + " " + std.getLastName() +
                " has added successfully";
    }

}
