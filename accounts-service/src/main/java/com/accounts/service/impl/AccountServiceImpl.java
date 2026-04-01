package com.accounts.service.impl;

import com.accounts.dto.CustomerDto;
import com.accounts.entity.Accounts;
import com.accounts.entity.Customer;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.request.AccountRequest;
import com.accounts.service.IAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;
    @Transactional
    @Override
    public void createAccount(AccountRequest accountRequest) throws AccountNotFoundException {
        Customer theCustomer = getCustomerByEmail(accountRequest.getCustomer().getEmail());
        Accounts theAccount = Accounts.builder()
                .accountType(accountRequest.getAccountType())
                .branchAddress(accountRequest.getBranchAddress())
                .customerId(theCustomer.getCustomerId())
                .build();
        accountsRepository.save(theAccount);
    }



    @Override
    public CustomerDto fetchAccount(String mobileNumber) throws AccountNotFoundException {
        Customer theCustomer = getCustomerByMobileNumber(mobileNumber);

        return CustomerDto.builder()
                .email(theCustomer.getEmail())
                .mobileNumber(theCustomer.getMobileNumber())
                .name(theCustomer.getName())
                .build();
    }

    @Override
    @Transactional
    public boolean updateAccount(AccountRequest accountRequest) throws AccountNotFoundException {
        Accounts theAccount = getAccountByAccountNumber(accountRequest.getAccountNumber());
        Customer theCustomer = getCustomerByEmail(accountRequest.getCustomer().getEmail());
        if(theAccount==null)
            return false;
        theAccount = Accounts.builder()
                .accountType(accountRequest.getAccountType())
                .branchAddress(accountRequest.getBranchAddress())
                .customerId(theCustomer.getCustomerId())
                .build();
        accountsRepository.save(theAccount);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteAccount(UUID accountNumber) throws AccountNotFoundException {
        Accounts theAccount = getAccountByAccountNumber(accountNumber);
        if(theAccount==null)
            return false;
        accountsRepository.delete(theAccount);
        return true;
    }


    private Customer getCustomerByEmail(String email) throws AccountNotFoundException {
        return customerRepository.findByEmail(email).orElseThrow(()-> new AccountNotFoundException(email));
    }
    private Customer getCustomerByMobileNumber(String mobileNumber) throws AccountNotFoundException {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new AccountNotFoundException(mobileNumber));
    }
    private Accounts getAccountByAccountNumber(UUID accountNumber) throws AccountNotFoundException {
        return accountsRepository.findByAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);

    }
}
