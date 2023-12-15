package org.service.accountservice.service.impl;

import lombok.AllArgsConstructor;
import org.service.accountservice.dto.*;
import org.service.accountservice.exception.ResourceNotFoundException;
import org.service.accountservice.mapper.AccountsMapper;
import org.service.accountservice.mapper.CustomerMapper;
import org.service.accountservice.model.Accounts;
import org.service.accountservice.model.Customer;
import org.service.accountservice.repository.AccountsRepository;
import org.service.accountservice.repository.CustomerRepository;
import org.service.accountservice.service.ICustomerDetailsService;
import org.service.accountservice.service.client.CardsFeinClient;
import org.service.accountservice.service.client.LoansFeinClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 14/12/23
 * @Description
 */

@Service
@AllArgsConstructor
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

    AccountsRepository accountsRepository;
    CustomerRepository customerRepository;
    LoansFeinClient loansFeinClient;
    CardsFeinClient cardsFeinClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        customerDetailsDto.setCardsDto(cardsFeinClient.fetchCardDetails(customer.getMobileNumber()).getBody());
        customerDetailsDto.setLoansDto(loansFeinClient.fetchLoanDetails(customer.getMobileNumber()).getBody());

        return customerDetailsDto;

    }
}
