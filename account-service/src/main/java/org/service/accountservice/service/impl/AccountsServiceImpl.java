package org.service.accountservice.service.impl;

import lombok.AllArgsConstructor;
import org.service.accountservice.constants.AccountsConstants;
import org.service.accountservice.dto.AccountsDto;
import org.service.accountservice.dto.CustomerDto;
import org.service.accountservice.exception.CustomerAlreadyExistException;
import org.service.accountservice.exception.ResourceNotFountException;
import org.service.accountservice.mapper.AccountsMapper;
import org.service.accountservice.mapper.CustomerMapper;
import org.service.accountservice.model.Account;
import org.service.accountservice.model.Customer;
import org.service.accountservice.repository.AccountsRepository;
import org.service.accountservice.repository.CustomersRepository;
import org.service.accountservice.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 10/12/23
 * @Description
 */

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;

    private CustomersRepository customersRepository;


    @Override
    public CustomerDto createAccount(CustomerDto customerDto) {
        Account newAccount = new Account();
        Long accountNumber = (long)(1000000 + new Random(9000000).nextInt());
        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS_ACCOUNT);
        Customer customer = CustomerMapper.toEntity(customerDto);
        if(customersRepository.findByMobileNumber(customerDto.getMobileNUmber()).isPresent()){
            throw new CustomerAlreadyExistException("Customer already exist with mobile number " + customerDto.getMobileNUmber());
        }
        customersRepository.save(customer);
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setBranchAddress(customerDto.getAccountsDto().getBranchAddress());
        accountsRepository.save(newAccount);
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        return false;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        return false;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFountException("customer", "mobileNumber", mobileNumber)
        );
        CustomerDto customerDto = CustomerMapper.toDto(customer);
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFountException("customer", "mobileNumber", customerDto.getCustomerId().toString())
        );
        AccountsDto accountsDto = AccountsMapper.toDto(account);
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }
}
