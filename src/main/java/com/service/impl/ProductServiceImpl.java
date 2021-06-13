package com.service.impl;

import com.common.Constants;
import com.dto.ResponseDto;
import com.entities.Product;
import com.exceptions.CustomException;
import com.repository.ProductRepo;
import com.repository.cache.MasterDataCache;
import com.service.ProductService;
import com.utils.Helper;
import com.utils.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.common.Constants.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private ProductRepo productRepo;

    @Override
    public ResponseDto createProduct(Product product) {

        if (Helper.notNull(productRepo.findByCode(product.getCode()))) {

            return new ResponseDto(ResponseStatus.error.toString(), PRODUCT_ALREADY_EXIST);
        }
        try {
            // save product
            LOGGER.info("Product saving..");
            productRepo.save(product);
            // initialize product list
            MasterDataCache.getDataMap(PRODUCTS);
            // success
            return new ResponseDto(ResponseStatus.success.toString(), PRODUCT_CREATED + " Product code : " + product.getCode() + " Product name : " + product.getName());
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
