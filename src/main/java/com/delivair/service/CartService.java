package com.delivair.service;

import com.delivair.common.payload.response.BaseResponse;
import com.delivair.exception.NotEnoughProductInStockException;
import com.delivair.model.Cart;
import com.delivair.model.Product;

public interface CartService {

    BaseResponse addProductToCart(Cart cart);

    BaseResponse delete(long productId);

//
//    void checkout() throws NotEnoughProductInStockException;
//
//    BaseResponse getDataFromCart();
}
