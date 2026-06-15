package com.employee.management.dto;

import com.employee.management.model.UserRole;
import lombok.Data;

@Data
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    private AddressDTO address;
}
