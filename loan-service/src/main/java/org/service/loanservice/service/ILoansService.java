package org.service.loanservice.service;

import org.service.loanservice.dto.LoansDto;
import org.service.loanservice.exception.LoanAlreadyExistsException;
import org.service.loanservice.exception.ResourceNotFoundException;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */
public interface ILoansService {
    public void createLoan(String mobileNumber) throws LoanAlreadyExistsException;

    public LoansDto fetchLoan(String mobileNumber);

    public boolean updateLoan(LoansDto loansDto);

    public boolean deleteLoan(String mobileNumber) throws ResourceNotFoundException;
}
