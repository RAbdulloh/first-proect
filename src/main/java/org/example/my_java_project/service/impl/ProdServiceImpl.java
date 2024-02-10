package org.example.my_java_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.ProdDto;
import org.example.my_java_project.exception.ContentNotFoundException;
import org.example.my_java_project.exception.DatabaseException;
import org.example.my_java_project.module.Product;
import org.example.my_java_project.repository.ProdRepository;
import org.example.my_java_project.service.ProdService;
import org.example.my_java_project.service.mapper.ProdMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {

    private final ProdMapper prodMapper;
    private final ProdRepository prodRepository;

    @Override
    public ApiResponse<ProdDto> createProd(ProdDto prodDto) {
        try {
            return ApiResponse.<ProdDto>builder()
                    .success(true)
                    .message("Ok")
                    .content(this.prodMapper.toDto(this.prodRepository.save(this.prodMapper.toEntity(prodDto))))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(String.format("Product while saving error! Massage %s", e.getMessage()));
        }
    }

    @Override
    public ApiResponse<ProdDto> getProd(Integer prodId) {
        Optional<Product> optional = this.prodRepository.findByProdIdAndDeleteAtIsNull(prodId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("Product with %d id is not found", prodId));
        }
        return ApiResponse.<ProdDto>builder()
                .success(true)
                .message("OK")
                .content(this.prodMapper.toDto(optional.get()))
                .build();
    }


    @Override
    public ApiResponse<ProdDto> updateProd(ProdDto prodDto, Integer prodId) {
        try {
            Optional<Product> optional = this.prodRepository.findByProdIdAndDeleteAtIsNull(prodId);
            if (optional.isEmpty()) {
                throw new ContentNotFoundException(String.format("Product with %d id is not found", prodId));
            }
            return ApiResponse.<ProdDto>builder()
                    .success(true)
                    .message(String.format("Product with %d id successful updated!", prodDto.getProdId()))
                    .content(this.prodMapper.toDto(optional.get()))
                    .build();
        } catch (Exception e) {
            return ApiResponse.<ProdDto>builder()
                    .code(-2)
                    .message(String.format("Product while updating error! Message %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<ProdDto> deleteProd(Integer prodId) {
        Optional<Product> optional = this.prodRepository.findByProdIdAndDeleteAtIsNull(prodId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("Product with %d id is not found", prodId));
        }

        Product product = optional.get();
        product.setDeleteAt(LocalDateTime.now());

        return ApiResponse.<ProdDto>builder()
                .success(true)
                .message(String.format("Product with %d id successful deleted!", prodId))
                .content(this.prodMapper.toDto(this.prodRepository.save(product)))
                .build();
    }
}

