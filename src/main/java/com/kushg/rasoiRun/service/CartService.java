package com.kushg.rasoiRun.service;

import com.kushg.rasoiRun.io.CartRequest;
import com.kushg.rasoiRun.io.CartResponse;

public interface CartService {

    public CartResponse addToCart(CartRequest request);

    CartResponse getCart();
    void clearCart();
    CartResponse removeFromCart(CartRequest cartRequest);
}
