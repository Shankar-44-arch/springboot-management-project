package com.employee.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data // Provide the Getters and Setters
@AllArgsConstructor // Provide all the parameterized constructors
@NoArgsConstructor // Provide the non-parameterized constructors
@Entity
@Table(name = "Employee_details")
public class Employee {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empid;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private UserRole userRole = UserRole.EMPLOYEE;

    // Creating the relationship between two Entity.
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
