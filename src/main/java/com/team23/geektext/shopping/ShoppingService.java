package com.team23.geektext.shopping;
import com.team23.geektext.book.Book;
import com.team23.geektext.repository.BookRepository;
import com.team23.geektext.repository.ShoppingCartRepository;
//import com.team23.geektext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class ShoppingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    public double getSubtotal(Long userId) {
        Shopping cart = userRepository.findById(userId).get().getCart();
        return cart.getBooks().stream().mapToDouble(Book::getPrice).sum();
        return 0;
    }

    public void addBookToCart(Long userId, Long bookId) {
       User user = userRepository.findById(userId).get();
       Book book = bookRepository.findById(bookId).get();
       Shopping cart = user.getCart();
       cart.getBooks().add(book);
       cartRepository.save(cart);
    }

    public List<Book> getBooksInCart(Long userId) {
        return userRepository.findById(userId).get().getCart().getBooks();
        return null;
    }

    public void deleteBookFromCart(Long userId, Long bookId) {
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        Shopping cart = user.getCart();
        cart.getBooks().remove(book);
        cartRepository.save(cart);
    }
}

