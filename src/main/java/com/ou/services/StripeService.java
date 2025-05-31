package com.ou.services;

import com.ou.dto.CartDto;
import com.ou.dto.StripeResponseDto;

public interface StripeService {
    StripeResponseDto checkoutProducts(CartDto cartDto);


}
