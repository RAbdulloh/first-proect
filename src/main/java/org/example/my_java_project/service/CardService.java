package org.example.my_java_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.CardDto;
import org.example.my_java_project.exception.CustomValidationException;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    ApiResponse<CardDto> createCard(CardDto cardDto) throws JsonProcessingException, CustomValidationException;

    ApiResponse<CardDto> getCard(Integer cardId);

    ApiResponse<CardDto> updateCard(CardDto cardDto, Integer cardId);

    ApiResponse<CardDto> deleteCard(Integer cardId);

}
