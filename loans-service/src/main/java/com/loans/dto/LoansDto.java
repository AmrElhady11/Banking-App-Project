package com.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoansDto {
    private String loanNumber;
    private String mobileNumber;
    private String loanType;
    private int totalAmount;
    private  int amountPaid;
    private int  outstandingAmount;
}
