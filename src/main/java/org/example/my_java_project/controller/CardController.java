package org.example.my_java_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.CardDto;
import org.example.my_java_project.service.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "card")
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ApiResponse<CardDto> createCard(@RequestBody @Valid CardDto cardDto) throws JsonProcessingException {
        return this.cardService.createCard(cardDto);
    }

    @GetMapping
    public ApiResponse<CardDto> getCard(@RequestParam(value = "id") Integer cardId) {
        return this.cardService.getCard(cardId);
    }

    @PutMapping
    public ApiResponse<CardDto> updateCard(@RequestBody CardDto cardDto,@RequestParam(value = "id") Integer cardId) {
        return this.cardService.updateCard(cardDto, cardId);
    }

    @DeleteMapping
    public ApiResponse<CardDto> deleteCard(@RequestParam(value = "id") Integer cardId) {
        return this.cardService.deleteCard(cardId);
    }

}
