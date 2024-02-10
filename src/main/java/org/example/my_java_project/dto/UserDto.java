package org.example.my_java_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.my_java_project.module.Card;
import org.example.my_java_project.module.Product;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "userPassword",allowSetters = true)
public class UserDto {

    private Integer userId;
    @NotBlank(message = "First Name cannot be null or empty!")
    private String firstName;
    @NotBlank(message = "Last Name cannot be null or empty!")
    private String lastName;
    @NotBlank(message = "User Email cannot be null or empty!")
    @Email
    private String userEmail;
    @NotBlank(message = "User Password cannot be null or empty!")
    private String userPassword;

    private List<Card> cards;
    private List<Product> products;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

}
