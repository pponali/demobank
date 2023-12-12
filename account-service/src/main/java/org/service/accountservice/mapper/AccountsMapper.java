package org.service.accountservice.mapper;

import org.service.accountservice.dto.AccountsDto;
import org.service.accountservice.model.Account;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */
public class AccountsMapper {
    public static AccountsDto toDto(Account account) {
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(account.getAccountNumber().toString());
        accountsDto.setAccountType(account.getAccountType());
        accountsDto.setBranchAddress(account.getBranchAddress());
        return accountsDto;
    }

    public static Account toEntity(AccountsDto accountDTO) {
        Account accounts = new Account();
        accounts.setAccountType(accountDTO.getAccountType());
        accounts.setBranchAddress(accountDTO.getBranchAddress());
        return accounts;
    }
}
