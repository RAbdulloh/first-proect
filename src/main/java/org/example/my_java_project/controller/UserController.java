package org.example.my_java_project.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.UserDto;
import org.example.my_java_project.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserDto> createUser(@RequestBody @Valid UserDto userDto) throws JsonProcessingException {
        return this.userService.createUser(userDto);
    }

    @GetMapping
    public ApiResponse<UserDto> getUser(@RequestParam(value = "id") Integer userId) {
        return this.userService.getUser(userId);
    }

    @PutMapping
    public ApiResponse<UserDto> updateUser(@RequestBody UserDto userDto, @RequestParam(value = "id") Integer userId) {
        return this.userService.updateUser(userDto, userId);
    }

    @DeleteMapping
    public ApiResponse<UserDto> deleteUser(@RequestParam(value = "id") Integer userId) {
        return this.userService.deleteUser(userId);
    }
}
