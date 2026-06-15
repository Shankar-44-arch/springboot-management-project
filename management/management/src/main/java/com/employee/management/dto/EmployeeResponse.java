package com.employee.management.dto;

import com.employee.management.model.UserRole;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"empId", "firstName", "lastName", "phone", "email", "userRole"})
public class EmployeeResponse {

    private Long empId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private UserRole userRole;

    private AddressDTO address;
}
