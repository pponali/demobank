package org.service.accounts.mapper;

import org.service.accounts.dto.CustomerDetailsDto;
import org.service.accounts.dto.CustomerDto;
import org.service.accounts.model.Customer;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */
public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        //customer.setCustomerId(customerDto.getCustomerId());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    public static Customer mapToCustomerDetailsDto(CustomerDetailsDto customerDetailsDto, Customer customer) {
        //customer.setCustomerId(customerDto.getCustomerId());
        customer.setMobileNumber(customerDetailsDto.getMobileNumber());
        customer.setName(customerDetailsDto.getName());
        customer.setEmail(customerDetailsDto.getEmail());
        return customer;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        customerDetailsDto.setEmail(customer.getEmail());
        return customerDetailsDto;
    }
}
