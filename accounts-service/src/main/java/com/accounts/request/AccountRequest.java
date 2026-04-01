package com.accounts.request;

import com.accounts.dto.CustomerDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {
    @NotEmpty(message = "Account number cannot be null or empty")
    private UUID accountNumber;
    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;
    @NotEmpty(message = "BranchAddress cannot be null or empty")
    private String branchAddress;
    private CustomerDto customer;
}
