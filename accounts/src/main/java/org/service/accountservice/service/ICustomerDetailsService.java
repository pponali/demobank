package org.service.accountservice.service;

import org.service.accountservice.dto.CustomerDetailsDto;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 14/12/23
 * @Description
 */
public interface ICustomerDetailsService {

    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
