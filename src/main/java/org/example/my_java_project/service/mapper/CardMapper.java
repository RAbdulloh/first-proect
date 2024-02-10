package org.example.my_java_project.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.CardDto;
import org.example.my_java_project.module.Card;
import org.example.my_java_project.module.User;
import org.example.my_java_project.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardMapper {

    private final UserRepository userRepository;

    public Card toEntity(CardDto dto){

        Optional<User> optional = this.userRepository.findByUserIdAndDeleteAtIsNull(dto.getUserId());
        if (optional.isPresent()){
            User user = optional.get();
            dto.setCardHolder(user.getFirstName() + " " + user.getLastName());
        }

        return Card.builder()
                .cardId(dto.getCardId())
                .user(dto.getUser())
                .cardHolder(dto.getCardHolder())
                .cardName(dto.getCardName())
                .cardNumber(dto.getCardNumber())
                .cardCode(dto.getCardCode())
                .userId(dto.getUserId())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .deleteAt(dto.getDeleteAt())
                .build();
    }

    public CardDto toDto(Card card){

        Optional<User> optional = this.userRepository.findByUserIdAndDeleteAtIsNull(card.getUserId());
        if (optional.isPresent()){
            User user = optional.get();
            card.setCardHolder(user.getFirstName() + " " + user.getLastName());
        }

        return CardDto.builder()
                .cardId(card.getCardId())
                .user(card.getUser())
                .cardHolder(card.getCardHolder())
                .cardName(card.getCardName())
                .cardNumber(card.getCardNumber())
                .cardCode(card.getCardCode())
                .userId(card.getUserId())
                .createAt(card.getCreateAt())
                .updateAt(card.getUpdateAt())
                .deleteAt(card.getDeleteAt())
                .build();
    }

}
