package org.example.my_java_project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.CardDto;
import org.example.my_java_project.dto.ErrorDto;
import org.example.my_java_project.exception.ContentNotFoundException;
import org.example.my_java_project.exception.CustomValidationException;
import org.example.my_java_project.exception.DatabaseException;
import org.example.my_java_project.module.Card;
import org.example.my_java_project.repository.CardRepository;
import org.example.my_java_project.service.CardService;
import org.example.my_java_project.service.mapper.CardMapper;
import org.example.my_java_project.service.validation.CardValidation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardValidation cardValidation;
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final ObjectMapper objectMapper;

    @Override
    public ApiResponse<CardDto> createCard(CardDto cardDto) throws JsonProcessingException {
        List<ErrorDto> errors = this.cardValidation.cardPostValidation(cardDto);
        if (!errors.isEmpty()) {
            throw new CustomValidationException(this.objectMapper.writeValueAsString(errors));
        }
        try {
            return ApiResponse.<CardDto>builder()
                    .success(true)
                    .message("Ok")
                    .content(this.cardMapper.toDto(this.cardRepository.save(this.cardMapper.toEntity(cardDto))))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(String.format("Card while saving error! Massage %s", e.getMessage()));
        }
    }

    @Override
    public ApiResponse<CardDto> getCard(Integer cardId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeleteAtIsNull(cardId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("Card with %d id is not found", cardId));
        }
        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ApiResponse<CardDto> updateCard(CardDto cardDto, Integer cardId) {
        List<ErrorDto> errors = this.cardValidation.cardPutValidation(cardDto);
        if (!errors.isEmpty()) {
            return ApiResponse.<CardDto>builder()
                    .code(-3)
                    .message("Validation Error!")
                    .errors(errors)
                    .build();
        }
        try {
            Optional<Card> optional = this.cardRepository.findByCardIdAndDeleteAtIsNull(cardId);
            if (optional.isEmpty()) {
                throw new ContentNotFoundException(String.format("Card with %d id is not found", cardId));
            }
            return ApiResponse.<CardDto>builder()
                    .success(true)
                    .message(String.format("Card with %d id successful updated!", cardDto.getCardId()))
                    .content(this.cardMapper.toDto(optional.get()))
                    .build();
        } catch (Exception e) {
            return ApiResponse.<CardDto>builder()
                    .code(-2)
                    .message(String.format("Card while updating error! Message %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<CardDto> deleteCard(Integer cardId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeleteAtIsNull(cardId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("Card with %d id is not found", cardId));
        }

        Card card = optional.get();
        card.setDeleteAt(LocalDateTime.now());

        return ApiResponse.<CardDto>builder()
                .success(true)
                .message(String.format("Card with %d id successful deleted!", cardId))
                .content(this.cardMapper.toDto(this.cardRepository.save(card)))
                .build();
    }
}
