package com.loans.service;

import com.loans.dto.LoansDto;

public interface ILoansService
{
    LoansDto createLoan(String mobileNumber);
    boolean updateLoan(LoansDto loan);
    LoansDto fetchLoans(String mobileNumber);
    boolean deleteLoan(String loanNumber);

}

