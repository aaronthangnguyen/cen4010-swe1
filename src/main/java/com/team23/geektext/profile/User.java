package com.team23.geektext.profile;

import com.team23.geektext.shopping.Shopping;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Shopping cart;

    public User() {}

    public User(String name) {
        this.name = name;
        this.cart = new Shopping(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Shopping getCart() {
        return cart;
    }

    public void setCart(Shopping cart) {
        this.cart = cart;
    }
}
