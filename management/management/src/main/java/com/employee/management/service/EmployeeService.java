package com.employee.management.service;

import com.employee.management.dto.AddressDTO;
import com.employee.management.dto.EmployeeResponse;
import com.employee.management.model.Address;
import com.employee.management.model.Employee;
import com.employee.management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> getAllEmpList() {
        return employeeRepository.findAll().stream()
                .map(this::mapToEmployeeResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmpById(Long id){
//        for(Employee emp : empList){
//            if(emp.getEmpid().equals(id)){
//                return emp;
//            }
//        }
        return employeeRepository.findById(id).map(this::mapToEmployeeResponse).orElse(null);

//      Instead of for loops we can use stream and filter to get and modify the data.
//        return empList.stream()
//                .filter(emp -> emp.getEmpid().equals(id))
//                .findFirst()
//                .orElse(null);
    }

    public String addEmpList(Employee emp){
        employeeRepository.save(emp);
        return "Employee is Added successfully...";
    }

    public EmployeeResponse editEmpList(Long id, Employee emp){
//        for(Employee empItm : empList){
//            if(empItm.getEmpid().equals(id)){
//                String old_name = empItm.getFirstName() + " " + empItm.getLastName();
//                empItm.setFirstName(emp.getFirstName());
//                empItm.setLastName(emp.getLastName());
//                return "Employee has been updated from " + old_name +
//                        " To " + emp.getFirstName() + " " + emp.getLastName();
//            }
//        }
//        return "Employee is not Found !!!";

//      Instead of for loops we can use stream and filter to get and modify the data.
//        Optional<Employee> isPresent =  empList.stream()
//                .filter(empItm -> empItm.getEmpid().equals(id))
//                .findFirst();

//        if(isPresent.isPresent()){
//            Employee empItm = isPresent.get();
//            empItm.setFirstName(emp.getFirstName());
//            empItm.setLastName(emp.getLastName());
//            return empItm;
//        }
//
        Employee empItm = employeeRepository.findById(id).orElse(null);
        if(empItm == null){
            return null;
        }

        empItm.setFirstName(emp.getFirstName());
        empItm.setLastName(emp.getLastName());
        empItm.setPhone(emp.getPhone());
        empItm.setEmail(emp.getEmail());
        empItm.setUserRole(emp.getUserRole());
        if(emp.getAddress() != null){
            empItm.setAddress(emp.getAddress());
        }else {
            empItm.setAddress(new Address());
        }
        employeeRepository.save(empItm);
        return employeeRepository.findById(id).map(this::mapToEmployeeResponse).orElse(null);
    }

    public String delEmp(Long id) {
//        for(int i = 0; i<empList.size(); i++){
//            if(empList.get(i).getEmpid().equals(id)){
//                Employee delEmp = empList.remove(i);
//                return "Employee " + delEmp.getFirstName() + " " +
//                        delEmp.getLastName() + " has been deleted successfully!!";
//            }
//        }
        employeeRepository.deleteById(id);
        return "Employee has been deleted!!!";
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmpId(employee.getEmpid());
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setPhone(employee.getPhone());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setUserRole(employee.getUserRole());

        if(employee.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(employee.getAddress().getId());
            addressDTO.setStreet(employee.getAddress().getStreet());
            addressDTO.setState(employee.getAddress().getState());
            addressDTO.setCity(employee.getAddress().getCity());
            addressDTO.setCountry(employee.getAddress().getCountry());
            addressDTO.setZipCode(employee.getAddress().getZipCode());
            employeeResponse.setAddress(addressDTO);
        }

        return employeeResponse;
    }

}
