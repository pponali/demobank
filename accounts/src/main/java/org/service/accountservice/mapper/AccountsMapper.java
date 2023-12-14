package org.service.accountservice.mapper;

import org.service.accountservice.dto.AccountsDto;
import org.service.accountservice.model.Accounts;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */
public class AccountsMapper {
    public static AccountsDto toDto(Accounts account) {
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(account.getAccountNumber());
        accountsDto.setAccountType(account.getAccountType());
        accountsDto.setBranchAddress(account.getBranchAddress());
        return accountsDto;
    }

    public static Accounts toEntity(AccountsDto accountDTO) {
        Accounts accounts = new Accounts();
        accounts.setAccountType(accountDTO.getAccountType());
        accounts.setBranchAddress(accountDTO.getBranchAddress());
        return accounts;
    }

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static void mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
    }
}
