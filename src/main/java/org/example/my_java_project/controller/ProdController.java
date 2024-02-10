package org.example.my_java_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.my_java_project.dto.ApiResponse;
import org.example.my_java_project.dto.ProdDto;
import org.example.my_java_project.service.ProdService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "product")
public class ProdController {

    private final ProdService prodService;

    @PostMapping
    public ApiResponse<ProdDto> createProd(@RequestBody @Valid ProdDto prodDto) {
        return this.prodService.createProd(prodDto);
    }

    @GetMapping
    public ApiResponse<ProdDto> getProd(@RequestParam(value = "Id") Integer prodId) {
        return this.prodService.getProd(prodId);
    }

    @PutMapping
    public ApiResponse<ProdDto> updateProd(@RequestBody ProdDto prodDto, @RequestParam(value = "Id") Integer prodId) {
        return this.prodService.updateProd(prodDto, prodId);
    }

    @DeleteMapping
    public ApiResponse<ProdDto> deleteProd(@RequestParam(value = "Id") Integer prodId) {
        return this.prodService.deleteProd(prodId);
    }

}
