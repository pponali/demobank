package org.service.accounts.service.impl;

import lombok.AllArgsConstructor;
import org.service.accounts.dto.*;
import org.service.accounts.exception.ResourceNotFoundException;
import org.service.accounts.mapper.AccountsMapper;
import org.service.accounts.mapper.CustomerMapper;
import org.service.accounts.model.Accounts;
import org.service.accounts.model.Customer;
import org.service.accounts.repository.AccountsRepository;
import org.service.accounts.repository.CustomerRepository;
import org.service.accounts.service.ICustomerDetailsService;
import org.service.accounts.service.client.CardsFeinClient;
import org.service.accounts.service.client.LoansFeinClient;
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
    public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        customerDetailsDto.setCardsDto(cardsFeinClient.fetchCardDetails(correlationId, mobileNumber).getBody());
        customerDetailsDto.setLoansDto(loansFeinClient.fetchLoanDetails(correlationId, mobileNumber).getBody());

        return customerDetailsDto;

    }
}
