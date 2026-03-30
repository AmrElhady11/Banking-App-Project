package com.accounts.service.impl;

import com.accounts.dto.CustomerDto;
import com.accounts.entity.Accounts;
import com.accounts.entity.Customer;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.request.AccountRequest;
import com.accounts.service.IAccountService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
                .customer(theCustomer)
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
        Accounts theAccount = getAccountByAccountNumber(accountRequest.getPublicAccountNumber());
        Customer theCustomer = getCustomerByEmail(accountRequest.getCustomer().getEmail());
        if(theAccount==null)
            return false;
        theAccount = Accounts.builder()
                .accountType(accountRequest.getAccountType())
                .branchAddress(accountRequest.getBranchAddress())
                .customer(theCustomer)
                .build();
        accountsRepository.save(theAccount);
        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        return false;
    }


    private Customer getCustomerByEmail(String email) throws AccountNotFoundException {
        return customerRepository.findByEmail(email).orElseThrow(()-> new AccountNotFoundException(email));
    }
    private Customer getCustomerByMobileNumber(String mobileNumber) throws AccountNotFoundException {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new AccountNotFoundException(mobileNumber));
    }
    private Accounts getAccountByAccountNumber(UUID accountNumber) throws AccountNotFoundException {
        return accountsRepository.findByPublicAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);

    }
}
