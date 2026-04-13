package com.accounts.controller;

import com.accounts.dto.CustomerDto;
import com.accounts.request.AccountRequest;
import com.accounts.response.AccountResponse;
import com.accounts.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@Validated
public class AccountController {
    private final IAccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createNewAccount(@Valid @RequestBody AccountRequest theAccountRequest) {
        accountService.createAccount(theAccountRequest);
        AccountResponse accountResponse = AccountResponse.builder()
                .statusMessage(String.format("Account created successfully with account number: %s", theAccountRequest.getAccountNumber()))
                .statusCode(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);

    }
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccount(@RequestParam("accountNumber") String accountNumber) {
    return  new ResponseEntity<>(accountService.fetchAccount(accountNumber), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<AccountResponse> updateAccount(@Valid @RequestBody AccountRequest theAccountRequest) {
        accountService.updateAccount(theAccountRequest);
        AccountResponse accountResponse = AccountResponse.builder()
                .statusMessage(String.format("Account updated successfully with account number: %s", theAccountRequest.getAccountNumber()))
                .statusCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AccountResponse> deleteAccount(@RequestParam("accountNumber") Integer accountNumber) {
        accountService.deleteAccount(accountNumber);
        AccountResponse accountResponse = AccountResponse.builder()
                .statusMessage(String.format("Account deleted successfully with account number: %s", accountNumber))
                .statusCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);

    }



}
