package com.cards.repository;

import com.cards.entity.Cards;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardsRepository extends CrudRepository<Cards, Integer> {
    Optional<Cards> findByCardNumber(String cardNumber);
    Optional<Cards> findByMobileNumber(String mobileNumber);
}
