package com.service;

import com.dto.CartItemDto;
import com.entities.Cart;
import com.entities.User;

public interface CartService {

    Cart addToCart(Cart cart, CartItemDto cartItemDto, User user);
}
