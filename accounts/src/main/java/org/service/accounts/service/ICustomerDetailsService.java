package org.service.accounts.service;

import org.service.accounts.dto.CustomerDetailsDto;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 14/12/23
 * @Description
 */
public interface ICustomerDetailsService {

    public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber);

}
