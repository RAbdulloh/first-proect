package org.example.my_java_project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private int code;
    private T content;
    private List<ErrorDto> errors;

}

