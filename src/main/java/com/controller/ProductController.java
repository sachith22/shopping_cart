package com.controller;

import com.dto.ResponseDto;
import com.entities.Product;
import com.exceptions.CustomException;
import com.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * This is product controller class
 */
@RestController
@CrossOrigin
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Add product method
     *
     * @param product
     * @return ResponseDto
     * @throws CustomException
     */
    @ApiOperation(value = "Add a Product")
    @PostMapping("/add")
    public ResponseDto createProduct(@RequestBody Product product) throws CustomException {

        return productService.createProduct(product);
    }
}
