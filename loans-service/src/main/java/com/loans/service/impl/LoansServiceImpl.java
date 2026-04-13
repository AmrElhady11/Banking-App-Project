package com.loans.service.impl;


import com.loans.dto.LoansDto;
import com.loans.entity.Loans;
import com.loans.exception.LoanAlreadyExistException;
import com.loans.exception.ResourceNotFoundException;
import com.loans.mapper.LoansMapper;
import com.loans.repository.LoansRepository;
import com.loans.service.ILoansService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private final LoansRepository LoansRepository;
    private final LoansMapper LoansMapper;

    @Override
    @Transactional
    public LoansDto createLoan(String mobileNumber) {
        Optional<Loans> Loans = LoansRepository.findByMobileNumber(mobileNumber);
        if (Loans.isPresent()) {
            throw new LoanAlreadyExistException("Loan already exist");
        }
        Loans newLoan = createNewLoan(mobileNumber);
           LoansRepository.save(newLoan);
           return LoansMapper.loansToDto(newLoan);
    }

    private Loans createNewLoan(String mobileNumber) {
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        return Loans.builder()
                .loanNumber(Long.toString(randomLoanNumber))
                .mobileNumber(mobileNumber)
                .loanType("FIN")
                .totalLoan(100000000)
                .amountPaid(0)
                .outstandingAmount(500)
                .build();

    }



    @Override
    @Transactional
    public boolean updateLoan(LoansDto LoanDto) {
        Loans Loan = LoansRepository.findByLoanNumber(LoanDto.getLoanNumber()).orElseThrow(()->new ResourceNotFoundException("Loan","LoanNumber",LoanDto.getLoanNumber()));
        LoansRepository.save(LoansMapper.dtoToLoans(LoanDto));
        return true;
    }

    @Override
    public LoansDto fetchLoans(String mobileNumber) {
       Loans Loan = LoansRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));
        System.out.println("/////////////////");

        return LoansMapper.loansToDto(Loan);
    }

    @Override
    public boolean deleteLoan(String LoanNumber) {
        Loans Loan = LoansRepository.findByLoanNumber(LoanNumber).orElseThrow(()->new ResourceNotFoundException("Loan","LoanNumber",LoanNumber));
        LoansRepository.delete(Loan);
        return true;
    }
}
