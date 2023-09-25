package com.team23.geektext.shopping;

import com.team23.geektext.repository.ShoppingCartRepository;

public class ShoppingService {
    private ShoppingCartRepository shoppingCartRepository;
    public ShoppingService(ShoppingCartRepository shoppingCartRepository){this.shoppingCartRepository = shoppingCartRepository;}

}
