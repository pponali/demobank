package org.service.loans.service;

import org.service.loans.exception.LoanAlreadyExistsException;
import org.service.loans.exception.ResourceNotFoundException;
import org.service.loans.dto.LoansDto;

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
