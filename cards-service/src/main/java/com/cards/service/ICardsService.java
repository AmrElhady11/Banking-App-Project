package com.cards.service;

import com.cards.dto.CardsDto;

public interface ICardsService {
    CardsDto createCard(String mobileNumber);
    boolean updateCard(CardsDto card);
    CardsDto fetchCards(String mobileNumber);
    boolean deleteCard(String cardNumber);

}
