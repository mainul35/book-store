package com.book.entity;

import com.book.entity.productInfo.Book;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Order extends DomainBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(targetEntity = Book.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable=false, name="book_id")
    private Book book = new Book();
    @Column
    private Integer quantity;
    @Column
    private BigDecimal basePrice = BigDecimal.valueOf(0D);
    @Column
    private BigDecimal itemTotal = BigDecimal.valueOf(0D);
    @Column
    private BigDecimal priceToBeDiscounted = BigDecimal.valueOf(0D);
    @Column
    private BigDecimal taxToBeAdded = BigDecimal.valueOf(0D);
    @Column(nullable = true)
    private String discountLog;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }

    public BigDecimal getPriceToBeDiscounted() {
        return priceToBeDiscounted;
    }

    public void setPriceToBeDiscounted(BigDecimal priceToBeDiscounted) {
        this.priceToBeDiscounted = priceToBeDiscounted;
    }

    public BigDecimal getTaxToBeAdded() {
        return taxToBeAdded;
    }

    public void setTaxToBeAdded(BigDecimal taxToBeAdded) {
        this.taxToBeAdded = taxToBeAdded;
    }
}
