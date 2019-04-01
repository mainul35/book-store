package com.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

public abstract class DomainBase {

    public abstract String getCreatedBy();

    public abstract void setCreatedBy(String createdBy);

    public abstract String getUpdatedBy();

    public abstract void setUpdatedBy(String updatedBy);

    public abstract Date getCreatedOn();

    public abstract void setCreatedOn(Date createdOn);

    @Column
    public abstract Date getUpdatedOn();

    public abstract void setUpdatedOn(Date updatedOn);
}
