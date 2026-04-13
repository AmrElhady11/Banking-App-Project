package com.cards.repository;

import com.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards, Integer> {
    Optional<Cards> findByCardNumber(String cardNumber);
    Optional<Cards> findByMobileNumber(String mobileNumber);
}
