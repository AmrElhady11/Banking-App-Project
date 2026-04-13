package com.cards.mapper;
import com.cards.dto.CardsDto;
import com.cards.entity.Cards;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardsMapper {
    CardsDto cardsToDto(Cards cards);
    Cards dtoToCards(CardsDto cardsDto);
}
