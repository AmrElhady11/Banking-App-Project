package com.accounts.mapper;

import com.accounts.dto.CustomerDto;
import com.accounts.entity.Accounts;
import com.accounts.request.AccountRequest;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
   CustomerDto toCustomerDto(Accounts theAccount);
   AccountRequest toAccountRequest(Accounts theAccount);
}
