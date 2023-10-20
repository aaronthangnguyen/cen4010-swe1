package com.team23.geektext.shopping;

import com.team23.geektext.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import java.util.LinkedList;

@RequestMapping("/api/shopping-cart")
@RestController
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingCartService;

    @GetMapping("/subtotal")
    public ResponseEntity<Double> getSubtotal(@RequestParam Long userId) {
        return ResponseEntity.ok(shoppingCartService.getSubtotal(userId));
    }

    @PostMapping("/addBook")
    public ResponseEntity<Void> addBookToCart(@RequestParam Long userId, @RequestParam Long bookId) {
        shoppingCartService.addBookToCart(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooksInCart(@RequestParam Long userId) {
        return ResponseEntity.ok(shoppingCartService.getBooksInCart(userId));
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<Void> deleteBookFromCart(@RequestParam Long userId, @RequestParam Long bookId) {
        shoppingCartService.deleteBookFromCart(userId, bookId);
        return ResponseEntity.ok().build();
    }
}
