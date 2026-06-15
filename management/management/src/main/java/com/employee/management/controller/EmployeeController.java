package com.employee.management.controller;

import com.employee.management.dto.EmployeeResponse;
import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

@CrossOrigin(origins = "*")
@RequestMapping("api/v1")
public class EmployeeController {

//    (1) Creating simple API
//    @GetMapping("/dashboard")
//    public String dashboard(){
//        return "Welcome to Dashboard....";
//    }
//
//    @GetMapping("/cart")
//    public String cart(){
//        return "The cart is Empty !!!";
//    }


//  (2) Creating simple REST API
//    public ArrayList<Employee> empList = new ArrayList<>();
//    public EmployeeController(){
//        empList.add(new Employee(1L, "Sam", "Gerald"));
//        empList.add(new Employee(2L, "Srinu", "B.I"));
//        empList.add(new Employee(3L, "Sarath", "S"));
//
//    }
//
//    @GetMapping("/empdetail")// GET API
//    public ArrayList<Employee> empDetail(){
//        return empList;
//    }
//
//    @PostMapping("/empdetail")// POST API
//    public String addEmp(@RequestBody Employee emp){
//        empList.add(emp);
//        return "Employee is Add Successfully ...";
//    }
//
//    @PutMapping("/empdetail/{id}")// PUT API
//    public String updateEmp(@PathVariable int id, @RequestBody Employee emp){
//        empList.set(id, emp);
//        return "Employee is Updated Successfully...";
//    }

//    (3)
    private final EmployeeService employeeService;

    @GetMapping("/emp")
    public ResponseEntity<List<EmployeeResponse>> getAllEmp(){
        //return employeeService.getAllEmpList();
        // Converting into ResponseEntity..
         return ResponseEntity.ok(employeeService.getAllEmpList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmp(@PathVariable Long id){
        //return employeeService.getEmpById(id);
        // Converting into ResponseEntity..
        EmployeeResponse empItm = employeeService.getEmpById(id);
        if(empItm == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empItm);
    }

    @PostMapping("/emp")
    public ResponseEntity<String> addEmp(@RequestBody Employee emp){
        //return employeeService.addEmpList(emp);
        // Converting into ResponseEntity..
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmpList(emp));
    }

    @PutMapping("/emp/{id}")
    public ResponseEntity<EmployeeResponse> editEmp(@PathVariable Long id, @RequestBody Employee emp){
        //return employeeService.editEmpList(id, emp);
        // Converting into ResponseEntity..
        EmployeeResponse empItm = employeeService.editEmpList(id, emp);
        if(empItm == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empItm);
    }

    @DeleteMapping("/empl/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Long id){
        //return employeeService.delEmp(id);
        // Converting into ResponseEntity..
        return ResponseEntity.ok(employeeService.delEmp(id));
    }

}
