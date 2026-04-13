package com.cards.service.impl;

import com.cards.dto.CardsDto;
import com.cards.entity.Cards;
import com.cards.exception.CardAlreadyExistException;
import com.cards.exception.ResourceNotFoundException;
import com.cards.mapper.CardsMapper;
import com.cards.repository.CardsRepository;
import com.cards.service.ICardsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private final CardsRepository cardsRepository;
    private final CardsMapper cardsMapper;

    @Override
    @Transactional
    public CardsDto createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if (cards.isPresent()) {
            throw new CardAlreadyExistException("Card already exist");
        }
        Cards newCard = createNewCard(mobileNumber);
           cardsRepository.save(newCard);
           return cardsMapper.cardsToDto(newCard);
    }

    private Cards createNewCard(String mobileNumber) {
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        return Cards.builder()
                .cardNumber(Long.toString(randomCardNumber))
                .mobileNumber(mobileNumber)
                .cardType("CREDIT")
                .totalLimit(100000000)
                .amountUsed(0)
                .availableAmount(500)
                .build();

    }



    @Override
    @Transactional
    public boolean updateCard(CardsDto cardDto) {
        Cards card = cardsRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(()->new ResourceNotFoundException("Card","cardNumber",cardDto.getCardNumber()));
        cardsRepository.save(cardsMapper.dtoToCards(cardDto));
        return true;
    }

    @Override
    public CardsDto fetchCards(String mobileNumber) {
       Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Card","mobileNumber",mobileNumber));
        return cardsMapper.cardsToDto(card);
    }

    @Override
    public boolean deleteCard(String cardNumber) {
        Cards card = cardsRepository.findByCardNumber(cardNumber).orElseThrow(()->new ResourceNotFoundException("Card","cardNumber",cardNumber));
        cardsRepository.delete(card);
        return true;
    }
}
