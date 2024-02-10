package org.example.my_java_project.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler {

    private final ObjectMapper objectMapper;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse<List<ErrorDto>> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<ErrorDto> errorDtoList = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {

            String fieldName = fieldError.getField();
            String rejectionValue = String.valueOf(fieldError.getRejectedValue());
            String detailMessage = fieldError.getDefaultMessage();
            errorDtoList.add(new ErrorDto(fieldName, String.format("Message %s, Rejection value %s", detailMessage, rejectionValue)));

        }
        return ApiResponse.<List<ErrorDto>>builder()
                .code(-3)
                .message("Validation Error")
                .errors(errorDtoList)
                .build();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ContentNotFoundException.class)
    public ApiResponse<Void> contentNotFoundException(ContentNotFoundException e) {
        return ApiResponse.<Void>builder()
                .code(-1)
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DatabaseException.class)
    public ApiResponse<Void> databaseException(DatabaseException e) {
        return ApiResponse.<Void>builder()
                .code(-2)
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CustomValidationException.class)
    public ApiResponse<List<ErrorDto>> customValidationException(CustomValidationException e) throws JsonProcessingException {
        return ApiResponse.<List<ErrorDto>>builder()
                .code(-3)
                .message(e.getMessage())
                .content(getFullResult(e.getMessage(), this.objectMapper))
                .build();
    }

    private List<ErrorDto> getFullResult(String join, ObjectMapper objectMapper) throws JsonProcessingException {
        List<ErrorDto> errors = new ArrayList<>();
        var apiResponse = objectMapper.readValue(join, List.class);
        for (Object o : apiResponse) {
            errors.add(this.objectMapper.readValue(this.objectMapper.writeValueAsString(o), ErrorDto.class));
        }

        return errors;
    }

}
