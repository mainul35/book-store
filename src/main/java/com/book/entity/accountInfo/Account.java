package com.book.entity.accountInfo;

import com.book.entity.DomainBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;
import javax.persistence.Id;

@Entity
public class Account extends DomainBase {
    @Id
    Long id;
    String name;
    String email;
    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Contact.class)
    Contact shippingContact = new Contact();
    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Contact.class)
    Contact billingContact = null;
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
