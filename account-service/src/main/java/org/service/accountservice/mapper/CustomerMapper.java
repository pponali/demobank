package org.service.accountservice.mapper;

import org.service.accountservice.dto.CustomerDto;
import org.service.accountservice.model.Customer;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */
public class CustomerMapper {
    public static Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        //customer.setCustomerId(customerDto.getCustomerId());
        customer.setMobileNumber(customerDto.getMobileNUmber());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }

    public static CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setName(customer.getName());
        customerDto.setMobileNUmber(customer.getMobileNumber());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }
}
