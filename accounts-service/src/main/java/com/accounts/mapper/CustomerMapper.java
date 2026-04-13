package com.accounts.mapper;

import com.accounts.dto.CustomerDto;
import com.accounts.entity.Customer;
import com.accounts.request.AccountRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
   CustomerDto toCustomerDto(Customer theCustomer);
   AccountRequest toCustomer(CustomerDto theCustomerDto);
}
