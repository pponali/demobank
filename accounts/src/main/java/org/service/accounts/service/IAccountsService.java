package org.service.accounts.service;

import org.service.accounts.dto.CustomerDto;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 10/12/23
 * @Description
 */
public interface IAccountsService {

    /**
     *THis method is responsible fore create an account
     * @param customerDto - customerDto
     */
    CustomerDto createAccount(CustomerDto customerDto);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

    CustomerDto fetchAccount(String mobileNumber);

}
