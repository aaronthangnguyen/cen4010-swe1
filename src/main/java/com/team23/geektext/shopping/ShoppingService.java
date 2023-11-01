package com.team23.geektext.shopping;

import com.team23.geektext.book.Book;
import com.team23.geektext.profile.User;
import com.team23.geektext.profile.UserRepository;
import com.team23.geektext.repository.BookRepository;
import com.team23.geektext.repository.ShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    public double getSubtotal(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getCart().getBooks().stream().mapToDouble(Book::getPrice).sum();
        }
        return 0;
    }

    public void addBookToCart(Long userId, UUID bookId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                user.getCart().getBooks().add(book);
                cartRepository.save(user.getCart());
            }
        }
    }

    public List<Book> getBooksInCart(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getCart().getBooks();
        }
        return null;
    }

    public void deleteBookFromCart(Long userId, UUID bookId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                user.getCart().getBooks().remove(book);
                cartRepository.save(user.getCart());
            }
        }
    }
}
