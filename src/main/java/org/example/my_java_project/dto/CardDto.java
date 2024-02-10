package org.example.my_java_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.my_java_project.module.User;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "cardCode",allowSetters = true)
public class CardDto {

    private Integer cardId;

    @NotBlank(message = "Card Name cannot be null or empty!")
    private String cardName;

    private String cardHolder;
    @NotBlank(message = "Card Number cannot be null or empty!")
    private String cardNumber;
    @NotBlank(message = "Card Code cannot be null or empty!")
    private String cardCode;
    @NotNull(message = "User Id cannot be null")
    private Integer userId;
    private User user;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

}
