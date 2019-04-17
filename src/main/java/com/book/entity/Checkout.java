package com.book.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Checkout extends DomainBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(targetEntity = Cart.class, fetch = FetchType.EAGER)
    private Order orderReference = new Order();
    @OneToOne(targetEntity = Cart.class, fetch = FetchType.EAGER)
    PaymentMethod paymentMethodReference = new PaymentMethod();
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
}
