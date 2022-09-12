package ru.clevertec.app.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.app.dto.CardDTO;
import ru.clevertec.app.entity.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDTO toCardDTO(Card card);

}
