package org.example.my_java_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.my_java_project.module.User;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdDto {

    private Integer prodId;

    @NotBlank(message = "Product Name cannot be null or empty!")
    private String prodName;
    @NotBlank(message = "Product Color cannot be null or empty!")
    private String prodColor;
    @NotBlank(message = "Product Type cannot be null or empty!")
    private String prodType;
    @NotBlank(message = "Product Price cannot be null or empty!")
    private String prodPrice;

    @NotNull(message = "User Id cannot be null!")
    private Integer userId;

    private User user;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

}
