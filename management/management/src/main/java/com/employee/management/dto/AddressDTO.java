package com.employee.management.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "street", "city", "state", "country", "zipCode"})
public class AddressDTO {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
