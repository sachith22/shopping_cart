package com.service;

import com.dto.ResponseDto;
import com.entities.Product;

import java.util.List;

public interface ProductService {

    ResponseDto createProduct(Product product);

    List<Product> findAll();
}
