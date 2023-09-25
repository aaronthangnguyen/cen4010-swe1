package com.team23.geektext.shopping;

import com.team23.geektext.book.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingController extends Book {
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService){
        this.shoppingService = shoppingService;
    }


}
