package com.service.impl;

import com.dto.ResponseDto;
import com.entities.Product;
import com.exceptions.CustomException;
import com.repository.ProductRepo;
import com.service.ProductService;
import com.utils.Helper;
import com.utils.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.common.Constants.*;

@Service
@CacheConfig(cacheNames = {"product"})
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepo productRepo;

    /**
     * Create product
     *
     * @param product
     * @return
     */
    @Caching(evict = {@CacheEvict(value = "allproductcache", allEntries = true),
            @CacheEvict(value = "productcache", key = "#product.id")
    })
    @Override
    public ResponseDto createProduct(Product product) {

        if (Helper.notNull(productRepo.findByCode(product.getCode()))) {

            return new ResponseDto(ResponseStatus.ERROR.toString(), PRODUCT_ALREADY_EXIST);
        }
        try {
            LOGGER.info("Product saving..");
            productRepo.save(product);
            return new ResponseDto(ResponseStatus.SUCCESS.toString(), PRODUCT_CREATED + " Product code : " + product.getCode() + " Product name : " + product.getName());
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "allproductcache")
    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
