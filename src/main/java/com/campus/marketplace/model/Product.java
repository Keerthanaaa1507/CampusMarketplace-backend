package com.campus.marketplace.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String category; // HOSTEL_ESSENTIALS, LAB_EQUIPMENT, TEXTBOOKS, ELECTRONICS, OTHER

    @Column(nullable = false)
    private String listingType; // FOR_SALE, FOR_TRADE, BOTH

    private Double price; // Price if for sale

    private String tradePreferences; // What seller is looking to trade for (e.g., "Looking for 3rd sem CS textbooks or electric iron")

    @Column(nullable = false)
    private String condition; // BRAND_NEW, LIKE_NEW, GOOD, FAIR

    @Column(nullable = false)
    private String status; // AVAILABLE, RESERVED, COMPLETED

    private String imageUrl;

    private Long sellerId;

    private String sellerName;

    private String sellerEmail;

    private String hostelLocation; // e.g. "Hostel Block B, Room 304"

    private String preferredPickupSpot; // e.g. "Hostel Gate 1" or "Library Lawn"

    private LocalDateTime createdAt = LocalDateTime.now();

    public Product() {}

    public Product(Long id, String title, String description, String category, 
                   String listingType, Double price, String tradePreferences, 
                   String condition, String status, String imageUrl, Long sellerId, 
                   String sellerName, String sellerEmail, String hostelLocation, 
                   String preferredPickupSpot) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.listingType = listingType;
        this.price = price;
        this.tradePreferences = tradePreferences;
        this.condition = condition;
        this.status = status;
        this.imageUrl = imageUrl;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.hostelLocation = hostelLocation;
        this.preferredPickupSpot = preferredPickupSpot;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getListingType() { return listingType; }
    public void setListingType(String listingType) { this.listingType = listingType; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getTradePreferences() { return tradePreferences; }
    public void setTradePreferences(String tradePreferences) { this.tradePreferences = tradePreferences; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Long getSellerId() { return sellerId; }
    public void setSellerId(Long sellerId) { this.sellerId = sellerId; }

    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }

    public String getSellerEmail() { return sellerEmail; }
    public void setSellerEmail(String sellerEmail) { this.sellerEmail = sellerEmail; }

    public String getHostelLocation() { return hostelLocation; }
    public void setHostelLocation(String hostelLocation) { this.hostelLocation = hostelLocation; }

    public String getPreferredPickupSpot() { return preferredPickupSpot; }
    public void setPreferredPickupSpot(String preferredPickupSpot) { this.preferredPickupSpot = preferredPickupSpot; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
