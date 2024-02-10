package org.example.my_java_project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ApiResponse;

import org.example.my_java_project.dto.ErrorDto;
import org.example.my_java_project.dto.UserDto;
import org.example.my_java_project.exception.ContentNotFoundException;
import org.example.my_java_project.exception.CustomValidationException;
import org.example.my_java_project.exception.DatabaseException;

import org.example.my_java_project.module.Card;
import org.example.my_java_project.module.User;

import org.example.my_java_project.repository.CardRepository;
import org.example.my_java_project.repository.UserRepository;
import org.example.my_java_project.service.UserService;

import org.example.my_java_project.service.mapper.UserMapper;

import org.example.my_java_project.service.validation.UserValidation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValidation userValidation;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final CardRepository cardRepository;

    @Override
    public ApiResponse<UserDto> createUser(UserDto userDto) throws JsonProcessingException {
        List<ErrorDto> errors = this.userValidation.userPostValidation(userDto);
        if (!errors.isEmpty()) {
            throw new CustomValidationException(this.objectMapper.writeValueAsString(errors));
        }

        try {
            return ApiResponse.<UserDto>builder()
                    .success(true)
                    .message("Ok")
                    .content(this.userMapper.toDto(userRepository.save(userMapper.toEntity(userDto))))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(String.format("User while saving error! Massage %s", e.getMessage()));
        }
    }

    @Override
    public ApiResponse<UserDto> getUser(Integer userId) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeleteAtIsNull(userId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("User with %d id is not found", userId));
        }
        return ApiResponse.<UserDto>builder()
                .success(true)
                .message("OK")
                .content(this.userMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ApiResponse<UserDto> updateUser(UserDto dto, Integer userId) {
        List<ErrorDto> errors = this.userValidation.userPutValidation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<UserDto>builder()
                    .code(-3)
                    .message(String.format("%s email already exists", dto.getUserEmail()))
                    .build();
        }
        String holderName = "";
        if (dto.getFirstName() != null) {
            holderName = holderName.concat(dto.getFirstName()).concat(" ");
        }
        if (dto.getLastName() != null) {
            holderName = holderName.concat(dto.getLastName());
        }

        holderName = holderName.trim();

        List<Card> cardList = this.cardRepository.findAllByUserIdAndDeleteAtIsNull(userId);

        for (Card card : cardList) {
            card.setCardHolder(holderName);
        }

        this.cardRepository.saveAll(cardList);

        Optional<User> optional = this.userRepository.findByUserIdAndDeleteAtIsNull(userId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("User with %d id is not found", userId));
        }
        try {
            User convertedUser = this.userMapper.convertNewUser(optional.get(), dto);
            return ApiResponse.<UserDto>builder()
                    .success(true)
                    .message(String.format("User with %d id successful updated", dto.getUserId()))
                    .content(this.userMapper.toDto(this.userRepository.save(convertedUser)))
                    .build();
        } catch (Exception e) {
            return ApiResponse.<UserDto>builder()
                    .code(-3)
                    .message(String.format("User while %s saving error", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<UserDto> deleteUser(Integer userId) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeleteAtIsNull(userId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("Card with %d id is not found", userId));
        }

        User user = optional.get();
        user.setDeleteAt(LocalDateTime.now());

        return ApiResponse.<UserDto>builder()
                .success(true)
                .message(String.format("Card with %d id successful deleted!", userId))
                .content(this.userMapper.toDto(this.userRepository.save(user)))
                .build();
    }
}
