package com.loans.controller;

import com.loans.dto.LoansDto;
import com.loans.dto.ResponseDto;
import com.loans.service.ILoansService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
@Validated
public class LoansController {
    private final ILoansService loansService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewAccount(@Valid @RequestParam String mobileNumber) {
        LoansDto theLoan =loansService.createLoan(mobileNumber);
        ResponseDto accountResponse = ResponseDto.builder()
                .message(String.format("Loan created successfully with Loan number: %s", theLoan.getLoanNumber()))
                .statusCode(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);

    }
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchAccount(@RequestParam("mobileNumber") String mobileNumber) {
    return  new ResponseEntity<>(loansService.fetchLoans(mobileNumber), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody LoansDto theLoan) {
        loansService.updateLoan(theLoan);
        ResponseDto response = ResponseDto.builder()
                .message(String.format("Loan updated successfully with account number: %s", theLoan.getLoanNumber()))
                .statusCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam("loanNumber") String loanNumber) {
        loansService.deleteLoan(loanNumber);
        ResponseDto response = ResponseDto.builder()
                .message(String.format("Loan deleted successfully with loan number: %s", loanNumber))
                .statusCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}
