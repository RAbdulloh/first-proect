package org.example.my_java_project.service;

import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.ProdDto;
import org.springframework.stereotype.Service;

@Service
public interface ProdService {
    ApiResponse<ProdDto> createProd(ProdDto prodDto);

    ApiResponse<ProdDto> getProd(Integer prodId);

    ApiResponse<ProdDto> updateProd(ProdDto prodDto, Integer prodId);

    ApiResponse<ProdDto> deleteProd(Integer prodId);

}
