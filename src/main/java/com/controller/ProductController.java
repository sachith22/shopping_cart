package com.controller;

import com.dto.ResponseDto;
import com.entities.Product;
import com.exceptions.CustomException;
import com.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param product
     * @return ResponseDto
     * @throws CustomException
     */
    @ApiOperation(value = "Add a Product")
    @PostMapping("/add")
    public ResponseDto createProduct(@RequestBody Product product) throws CustomException {

        return productService.createProduct(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> body = productService.findAll();
        return new ResponseEntity<List<Product>>(body, HttpStatus.OK);
    }
}
