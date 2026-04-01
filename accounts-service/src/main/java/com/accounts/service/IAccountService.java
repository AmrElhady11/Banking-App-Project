package com.accounts.service;

import com.accounts.dto.CustomerDto;
import com.accounts.request.AccountRequest;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

public interface IAccountService {
    void createAccount(AccountRequest accountRequest) throws AccountNotFoundException;
    CustomerDto fetchAccount(String mobileNumber) throws AccountNotFoundException;
    boolean updateAccount(AccountRequest accountRequest) throws AccountNotFoundException;
    boolean deleteAccount(UUID accountNumber) throws AccountNotFoundException;
}
