package com.eazybank.accounts.mapper;

import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.entity.Customer;

public class CustomerMapper {

    public static  CustomerDto mapToCustomerDTO(Customer customer, CustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());

        return customerDto;
    }

    public static Customer mapToCustomerEntity(CustomerDto customerDto, Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customer;
    }
}
