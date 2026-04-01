package com.accounts.mapper;

import com.accounts.entity.Accounts;
import com.accounts.request.AccountRequest;
import org.mapstruct.Mapper;

@Mapper
public interface AccountsMapper {
    AccountRequest toAccountRequest(Accounts theAccount);
    Accounts toAccounts(AccountRequest theAccountRequest);
}
