package com.accounts.service;

import com.accounts.dto.CustomerDto;
import com.accounts.exception.ResourceNotFoundException;
import com.accounts.request.AccountRequest;
import java.util.UUID;

public interface IAccountService {
    void createAccount(AccountRequest accountRequest) throws ResourceNotFoundException;
    CustomerDto fetchAccount(String mobileNumber) throws ResourceNotFoundException;
    boolean updateAccount(AccountRequest accountRequest) throws ResourceNotFoundException;
    boolean deleteAccount(Integer accountNumber) throws ResourceNotFoundException;
}
