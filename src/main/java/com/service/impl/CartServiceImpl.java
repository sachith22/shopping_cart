package com.service.impl;

import com.dto.CartItemDto;
import com.entities.Cart;
import com.entities.CartItem;
import com.entities.Product;
import com.entities.User;
import com.repository.ProductRepo;
import com.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Cart addToCart(Cart cart, CartItemDto cartItemDto, User user) {
        Map<String, CartItem> cartItemMap = new HashMap<>();

        List<Product> productList = productRepo.findAll();
        CartItem cartItem = null;
        Product product = null;

        for (Object o : productList) {
            product = (Product) o;
            if (product.getCode().equalsIgnoreCase(cartItemDto.getProductCode())) {
                if (cart != null) {
                    cartItemMap = cart.getCartItems();
                    cartItem = cartItemMap.get(product.getCode());
                    if (cartItem != null) {
                        cartItem.setQuantity(cartItemDto.getQuantity());
                        cart.setTotalAmount(calculateTotalCartAmount(cart));
                        //set total cart amount
                        cart.setShipmentCost(calculateShipmentCost(cart));
                        //set vat amount
                        cart.setTotalVAT(calculateVATAmount(cart));
                    } else {
                        cartItem = new CartItem();
                        cartItem.setQuantity(cartItemDto.getQuantity());
                        cartItem.setProduct(product);
                        cartItemMap.put(product.getCode(), cartItem);
                        cart.setTotalAmount(calculateTotalCartAmount(cart));
                        //set total cart amount
                        cart.setShipmentCost(calculateShipmentCost(cart));
                        //set vat amount
                        cart.setTotalVAT(calculateVATAmount(cart));
                    }
                } else {
                    cartItem = new CartItem();
                    //cartItem.setId(user.getId());
                    cartItem.setProduct(product);
                    cartItem.setQuantity(cartItemDto.getQuantity());
                    cart = new Cart();
                    cart.setUser(user);
                    cartItemMap.put(product.getCode(), cartItem);
                    cart.setCartItems(cartItemMap);
                    cartItem.setCart(cart);
                    cart.setTotalAmount(calculateTotalCartAmount(cart));
                    //set total cart amount
                    cart.setShipmentCost(calculateShipmentCost(cart));
                    //set vat amount
                    cart.setTotalVAT(calculateVATAmount(cart));
                }
            } else {
                //TODO validate in future
                LOGGER.info("Product item not found");
            }
        }

        return cart;
    }

    /**
     * Calculate total amount of cart items
     *
     * @param cart
     * @return totalCartAmount
     */
    private double calculateTotalCartAmount(Cart cart) {
        double totalCartAmount = 0;
        for (Map.Entry<String, CartItem> cartItemMap : cart.getCartItems().entrySet()) {
            CartItem cartItem = cartItemMap.getValue();
            totalCartAmount += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        }
        return totalCartAmount + calculateShipmentCost(cart) + calculateVATAmount(cart);
    }

    /**
     * Must implement
     *
     * @return totalShipmentCost
     */
    private Double calculateShipmentCost(Cart cart) {
        //TODO must implement logic as business requirement

        return (double) 7;
    }

    /**
     * Must implement
     *
     * @return totalVATAmount
     */
    private Double calculateVATAmount(Cart cart) {
        //TODO must implement logic as business requirement

        return (double) 10;
    }
}
