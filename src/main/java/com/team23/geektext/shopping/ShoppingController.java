package com.team23.geektext.shopping;

import com.team23.geektext.book.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingCartService;

    @GetMapping("/subtotal")
    public ResponseEntity<Double> getSubtotal(@RequestParam Long userId) {
        double subtotal = shoppingCartService.getSubtotal(userId);
        if (subtotal >= 0) {
            return ResponseEntity.ok(subtotal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<Void> addBookToCart(@RequestParam Long userId, @RequestParam UUID bookId) {
        shoppingCartService.addBookToCart(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooksInCart(@RequestParam Long userId) {
        List<Book> books = shoppingCartService.getBooksInCart(userId);
        if (books != null) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<Void> deleteBookFromCart(@RequestParam Long userId, @RequestParam UUID bookId) {
        shoppingCartService.deleteBookFromCart(userId, bookId);
        return ResponseEntity.ok().build();
    }
}
