package com.book.entity;

import com.book.entity.accountInfo.Account;
import com.book.entity.auth.User;
import com.book.entity.productInfo.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cart extends DomainBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToMany(orphanRemoval=true, fetch= FetchType.LAZY)
    List<Order> cartItems = new ArrayList<>();
    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    Account cartOwner = new Account();
    @Column
    private String createdBy;
    @Column
    private String updatedBy;
    @Column
    private Date createdOn;
    @Column
    private Date updatedOn;
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Date getCreatedOn() {
        return this.createdOn;
    }

    @Override
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    @Override
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getCartOwner() {
        return cartOwner;
    }

    public void setCartOwner(Account cartOwner) {
        this.cartOwner = cartOwner;
    }

    public List<Order> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Order> cartItems) {
        this.cartItems = cartItems;
    }
}
