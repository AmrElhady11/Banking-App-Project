package com.accounts.service;

import com.accounts.dto.CustomerDto;
import com.accounts.request.AccountRequest;

import javax.security.auth.login.AccountNotFoundException;

public interface IAccountService {
    void createAccount(AccountRequest accountRequest) throws AccountNotFoundException;
    CustomerDto fetchAccount(String mobileNumber) throws AccountNotFoundException;
    boolean updateAccount(AccountRequest accountRequest) throws AccountNotFoundException;
    boolean deleteAccount(String mobileNumber);
}
