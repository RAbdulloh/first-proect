package org.example.my_java_project.service.validation;


import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ErrorDto;
import org.example.my_java_project.dto.UserDto;

import org.example.my_java_project.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository userRepository;

    public List<ErrorDto> userPostValidation(UserDto userDto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (this.userRepository.existsByUserEmailAndDeleteAtIsNull(userDto.getUserEmail())) {
            errors.add(new ErrorDto("User Email", String.format("This email %s already exist!", userDto.getUserEmail())));
        }
        return errors;
    }

    public List<ErrorDto> userPutValidation(UserDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (this.userRepository.existsByUserEmailAndDeleteAtIsNull(dto.getUserEmail())) {
            errors.add(new ErrorDto("User Email", String.format("This email %s already exist!", dto.getUserEmail())));
        }
        return errors;
    }
}
