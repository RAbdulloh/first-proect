package org.example.my_java_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ApiResponse<UserDto> createUser(UserDto userDto) throws JsonProcessingException;

    ApiResponse<UserDto> getUser(Integer userId);

    ApiResponse<UserDto> updateUser(UserDto userDto, Integer userId);

    ApiResponse<UserDto> deleteUser(Integer userId);

}

