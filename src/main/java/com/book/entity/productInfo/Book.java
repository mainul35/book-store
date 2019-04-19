package com.book.entity.productInfo;
import com.book.entity.Attachment;
import com.book.entity.DomainBase;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
public class Book extends DomainBase {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
//	@ElementCollection(fetch = FetchType.EAGER)
	private String authors;
	private String publisher;
	private String publicationDate;
	private String language;
	@OneToOne
	private Category category = new Category();
	private int numberOfPages;
	private String format;
	private int isbn;
	private double shippingWeight;
	private BigDecimal originalPrice = new BigDecimal(0);

    private boolean isUsed;
    private BigDecimal usedPrice = new BigDecimal(0);


//    @OneToOne
//    private Offer currentOffer = new Offer();

    private boolean active=true;
	
	@Column(columnDefinition="text")
	private String description;
	private int inStockNumber;

    @Column
    private String createdBy;
    @Column
    private String updatedBy;
    @Column
    private Date createdOn;
    @Column
    private Date updatedOn;
	
	@OneToOne
	private Attachment photo = new Attachment();

    public Book() {
        this.id = System.currentTimeMillis();
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public double getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

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
        this.createdBy = updatedBy;
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public BigDecimal getUsedPrice() {
        return usedPrice;
    }

    public void setUsedPrice(BigDecimal usedPrice) {
        this.usedPrice = usedPrice;
    }



    public Attachment getPhoto() {
        return photo;
    }

    public void setPhoto(Attachment photo) {
        this.photo = photo;
    }


//    public Offer getCurrentOffer() {
//        return currentOffer;
//    }
//
//    public void setCurrentOffer(Offer currentOffer) {
//        this.currentOffer = currentOffer;
//    }
}
