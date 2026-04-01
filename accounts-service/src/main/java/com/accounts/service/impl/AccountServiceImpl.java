package com.accounts.service.impl;

import com.accounts.dto.CustomerDto;
import com.accounts.entity.Accounts;
import com.accounts.entity.Customer;
import com.accounts.exception.ResourceNotFoundException;
import com.accounts.mapper.AccountsMapper;
import com.accounts.mapper.CustomerMapper;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.request.AccountRequest;
import com.accounts.service.IAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;
    private final AccountsMapper accountsMapper;
    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public void createAccount(AccountRequest accountRequest) throws ResourceNotFoundException {
        getCustomerByEmail(accountRequest.getCustomer().getEmail());
        Accounts theAccount = accountsMapper.toAccounts(accountRequest);
        accountsRepository.save(theAccount);
    }



    @Override
    public CustomerDto fetchAccount(String mobileNumber) throws ResourceNotFoundException {
        Customer theCustomer = getCustomerByMobileNumber(mobileNumber);

        return customerMapper.toCustomerDto(theCustomer);
    }

    @Override
    @Transactional
    public boolean updateAccount(AccountRequest accountRequest) throws ResourceNotFoundException {
        Accounts theAccount = getAccountByAccountNumber(accountRequest.getAccountNumber());
        Customer theCustomer = getCustomerByEmail(accountRequest.getCustomer().getEmail());
        if(theAccount==null)
            return false;
        theAccount = accountsMapper.toAccounts(accountRequest);
        accountsRepository.save(theAccount);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteAccount(UUID accountNumber) throws ResourceNotFoundException {
        Accounts theAccount = getAccountByAccountNumber(accountNumber);
        if(theAccount==null)
            return false;
        accountsRepository.delete(theAccount);
        return true;
    }


    private Customer getCustomerByEmail(String email) throws ResourceNotFoundException {
        return customerRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Customer","email",email));
    }
    private Customer getCustomerByMobileNumber(String mobileNumber) throws ResourceNotFoundException {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
    }
    private Accounts getAccountByAccountNumber(UUID accountNumber) throws ResourceNotFoundException{
        return accountsRepository.findByAccountNumber(accountNumber).orElseThrow(()-> new ResourceNotFoundException("Account","accountNumber",accountNumber.toString()));

    }
}
