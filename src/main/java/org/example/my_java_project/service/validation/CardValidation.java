package org.example.my_java_project.service.validation;

import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.CardDto;
import org.example.my_java_project.dto.ErrorDto;
import org.example.my_java_project.repository.CardRepository;
import org.example.my_java_project.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardValidation {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public List<ErrorDto> cardPutValidation(CardDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (this.cardRepository.existsByCardNumberAndDeleteAtIsNull(dto.getCardNumber())) {
            errors.add(new ErrorDto("Card Number", String.format("This Card Number %s already exist!", dto.getCardNumber())));
        }
        return errors;
    }

    public List<ErrorDto> cardPostValidation(CardDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (this.cardRepository.existsByCardNumberAndDeleteAtIsNull(dto.getCardNumber())) {
            errors.add(new ErrorDto("Card Number", String.format("This Card Number %s already exist!", dto.getCardNumber())));
        }
        if (!this.userRepository.existsByUserIdAndDeleteAtIsNull(dto.getUserId())) {
            errors.add(new ErrorDto("User Id", String.format("User with %d id is not found!", dto.getUserId())));
        }
        return errors;
    }
}
