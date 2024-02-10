package org.example.my_java_project.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ProdDto;
import org.example.my_java_project.module.Product;
import org.springframework.stereotype.Component;

@Component
public class ProdMapper {

    public Product toEntity(ProdDto dto) {
        return Product.builder()
                .prodId(dto.getProdId())
                .prodName(dto.getProdName())
                .prodColor(dto.getProdColor())
                .prodType(dto.getProdType())
                .prodPrice(dto.getProdPrice())
                .user(dto.getUser())
                .userId(dto.getUserId())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .deleteAt(dto.getDeleteAt())
                .build();
    }

    public ProdDto toDto(Product product) {
        return ProdDto.builder()
                .prodId(product.getProdId())
                .prodName(product.getProdName())
                .prodColor(product.getProdColor())
                .prodType(product.getProdType())
                .prodPrice(product.getProdPrice())
                .user(product.getUser())
                .userId(product.getUserId())
                .createAt(product.getCreateAt())
                .updateAt(product.getUpdateAt())
                .deleteAt(product.getDeleteAt())
                .build();
    }

}
