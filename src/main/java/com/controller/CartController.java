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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This is Cart Controller
 */
@RestController
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    private final AuthenticationService authenticationService;

    private final HttpSession httpSession;

    public CartController(CartService cartService, AuthenticationService authenticationService, HttpSession httpSession) {
        this.cartService = cartService;
        this.authenticationService = authenticationService;
        this.httpSession = httpSession;
    }

    /**
     * Add item to cart
     * @param cartItemDto
     * @param token
     * @return cart
     */
    @ApiOperation(value = "Add to cart")
    @PostMapping("add")
    public ResponseEntity<Cart> addToCart(@RequestBody CartItemDto cartItemDto, @RequestParam("token") String token) {
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
     * Calculate cart
     * @param token
     * @param request
     * @return
     */
    @ApiOperation(value = "Calculate the cart")
    @PostMapping("calculate")
    public ResponseEntity<Cart> calculateCart(@RequestParam("token") String token, HttpServletRequest request) {
        Cart cart = null;
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        if (user != null && request.getSession() != null) {
            if (request.getSession().getAttribute(user.getUsername()) != user.getUsername()) {
                cart = (Cart) request.getSession().getAttribute(user.getUsername());

                return new ResponseEntity<Cart>(cart, HttpStatus.OK);
            }
        }

        return new ResponseEntity<Cart>(cart, HttpStatus.NOT_FOUND);
    }
}
