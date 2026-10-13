package com.campus.marketplace.dto;

public class ProductRequest {
    private String title;
    private String description;
    private String category;
    private String listingType;
    private Double price;
    private String tradePreferences;
    private String condition;
    private String imageUrl;
    private Long sellerId;
    private String hostelLocation;
    private String preferredPickupSpot;

    public ProductRequest() {}

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

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Long getSellerId() { return sellerId; }
    public void setSellerId(Long sellerId) { this.sellerId = sellerId; }

    public String getHostelLocation() { return hostelLocation; }
    public void setHostelLocation(String hostelLocation) { this.hostelLocation = hostelLocation; }

    public String getPreferredPickupSpot() { return preferredPickupSpot; }
    public void setPreferredPickupSpot(String preferredPickupSpot) { this.preferredPickupSpot = preferredPickupSpot; }
}
