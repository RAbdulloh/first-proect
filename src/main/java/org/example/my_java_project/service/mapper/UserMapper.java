package org.example.my_java_project.service.mapper;

import org.example.my_java_project.dto.UserDto;
import org.example.my_java_project.module.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        return User.builder()
                .userId(dto.getUserId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .userEmail(dto.getUserEmail())
                .userPassword(dto.getUserPassword())
                .updateAt(dto.getUpdateAt())
                .createAt(dto.getCreateAt())
                .deleteAt(dto.getDeleteAt())
                .cards(dto.getCards())
                .products(dto.getProducts())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userEmail(user.getUserEmail())
                .userPassword(user.getUserPassword())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .deleteAt(user.getDeleteAt())
                .cards(user.getCards())
                .products(user.getProducts())
                .build();
    }

    public User convertNewUser(User user, UserDto dto) {
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getUserId() != null) {
            user.setUserId(dto.getUserId());
        }
        if (dto.getUserPassword() != null) {
            user.setUserPassword(dto.getUserPassword());
        }
        if (dto.getCards() != null) {
            user.setCards(dto.getCards());
        }
        if (dto.getUserEmail() != null) {
            user.setUserEmail(dto.getUserEmail());
        }
        if (dto.getCreateAt() != null) {
            user.setCreateAt(dto.getCreateAt());
        }
        if (dto.getUpdateAt() != null) {
            user.setUpdateAt(dto.getUpdateAt());
        }
        if (dto.getDeleteAt() != null) {
            user.setDeleteAt(dto.getDeleteAt());
        }
        return user;
    }

}
