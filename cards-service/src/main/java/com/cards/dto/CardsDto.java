package com.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {
    private String cardNumber;
    private String mobileNumber;
    private String cardType;
    private int totalAmount;
    private  int amountUsed;
    private int  availableAmount;
}
