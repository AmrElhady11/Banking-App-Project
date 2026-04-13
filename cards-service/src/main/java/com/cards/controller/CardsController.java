package com.cards.controller;

import com.cards.dto.CardsDto;
import com.cards.dto.ResponseDto;
import com.cards.service.ICardsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
@Validated
public class CardsController {
    private final ICardsService cardsService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewAccount(@Valid @RequestParam String mobileNumber) {
        CardsDto theCard =cardsService.createCard(mobileNumber);
        ResponseDto accountResponse = ResponseDto.builder()
                .message(String.format("Card created successfully with Card number: %s", theCard.getCardNumber()))
                .statusCode(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);

    }
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchAccount(@RequestParam("mobileNumber") String mobileNumber) {
    return  new ResponseEntity<>(cardsService.fetchCards(mobileNumber), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CardsDto theCard) {
        cardsService.updateCard(theCard);
        ResponseDto response = ResponseDto.builder()
                .message(String.format("Card updated successfully with account number: %s", theCard.getCardNumber()))
                .statusCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam("cardNumber") String cardNumber) {
        cardsService.deleteCard(cardNumber);
        ResponseDto response = ResponseDto.builder()
                .message(String.format("Card deleted successfully with card number: %s", cardNumber))
                .statusCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}
