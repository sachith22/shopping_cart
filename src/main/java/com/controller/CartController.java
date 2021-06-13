package com.controller;

import com.dto.CartItemDto;
import com.entities.Cart;
import com.entities.User;
import com.service.CartService;
import com.service.impl.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * This is Cart Controller
 */
@RestController
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    private final AuthenticationService authenticationService;

    public CartController(CartService cartService, AuthenticationService authenticationService) {
        this.cartService = cartService;
        this.authenticationService = authenticationService;
    }

    /**
     * Add item to cart method
     *
     * @param httpSession
     * @param cartItemDto
     * @param token
     * @return cart
     */
    @ApiOperation(value = "Add to cart")
    @PostMapping("add")
    public ResponseEntity<Cart> addToCart(HttpSession httpSession, @RequestBody CartItemDto cartItemDto, @RequestParam("token") String token) {
        Cart cart = null;
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        if (user != null && httpSession != null) {
            if (httpSession.getAttribute(user.getUsername()) == null) {
                cart = cartService.addToCart(null, cartItemDto, user);
                httpSession.setAttribute(user.getUsername(), cart);
            } else {
                cart = cartService.addToCart((Cart) httpSession.getAttribute(user.getUsername()), cartItemDto, user);
                httpSession.setAttribute(user.getUsername(), cart);
            }
        }

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    /**
     * Re-calculate cart method
     *
     * @param httpSession
     * @param cartItemDto
     * @param token
     * @return cart
     */
    @ApiOperation(value = "Calculate cart")
    @PostMapping("calculate")
    public ResponseEntity<Cart> reCalculateCart(HttpSession httpSession, @RequestBody CartItemDto cartItemDto, @RequestParam("token") String token) {
        Cart cart = null;
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        if (user != null && httpSession != null) {
            if (httpSession.getAttribute(user.getUsername()) != null) {
                cart = cartService.addToCart((Cart) httpSession.getAttribute(user.getUsername()), cartItemDto, user);
                httpSession.setAttribute(user.getUsername(), cart);

                return new ResponseEntity<Cart>(cart, HttpStatus.OK);
            }
        }

        return new ResponseEntity<Cart>(cart, HttpStatus.NOT_FOUND);
    }
}
