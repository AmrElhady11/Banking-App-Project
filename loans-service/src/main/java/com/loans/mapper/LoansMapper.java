package com.loans.mapper;

import com.loans.dto.LoansDto;
import com.loans.entity.Loans;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoansMapper {
    LoansDto loansToDto(Loans loans);
    Loans dtoToLoans(LoansDto loansDto);
}
