package com.campus.marketplace.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade_requests")
public class TradeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who wants to trade
    private Long requesterId;
    private String requesterName;
    private String requesterEmail;

    // The target product they want to acquire
    private Long targetProductId;
    private String targetProductTitle;
    private Long targetSellerId;
    private String targetSellerName;

    // The product or item offered in exchange
    private Long offeredProductId; // Optional if from existing product list
    private String offeredItemTitle;
    private String offeredItemDescription;

    @Column(length = 1000)
    private String message;

    // Status: PENDING, ACCEPTED, REJECTED, COMPLETED, CANCELLED
    @Column(nullable = false)
    private String status = "PENDING";

    // Exchange logistics
    private String pickupSpot;
    private String meetingTime;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public TradeRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRequesterId() { return requesterId; }
    public void setRequesterId(Long requesterId) { this.requesterId = requesterId; }

    public String getRequesterName() { return requesterName; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }

    public String getRequesterEmail() { return requesterEmail; }
    public void setRequesterEmail(String requesterEmail) { this.requesterEmail = requesterEmail; }

    public Long getTargetProductId() { return targetProductId; }
    public void setTargetProductId(Long targetProductId) { this.targetProductId = targetProductId; }

    public String getTargetProductTitle() { return targetProductTitle; }
    public void setTargetProductTitle(String targetProductTitle) { this.targetProductTitle = targetProductTitle; }

    public Long getTargetSellerId() { return targetSellerId; }
    public void setTargetSellerId(Long targetSellerId) { this.targetSellerId = targetSellerId; }

    public String getTargetSellerName() { return targetSellerName; }
    public void setTargetSellerName(String targetSellerName) { this.targetSellerName = targetSellerName; }

    public Long getOfferedProductId() { return offeredProductId; }
    public void setOfferedProductId(Long offeredProductId) { this.offeredProductId = offeredProductId; }

    public String getOfferedItemTitle() { return offeredItemTitle; }
    public void setOfferedItemTitle(String offeredItemTitle) { this.offeredItemTitle = offeredItemTitle; }

    public String getOfferedItemDescription() { return offeredItemDescription; }
    public void setOfferedItemDescription(String offeredItemDescription) { this.offeredItemDescription = offeredItemDescription; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPickupSpot() { return pickupSpot; }
    public void setPickupSpot(String pickupSpot) { this.pickupSpot = pickupSpot; }

    public String getMeetingTime() { return meetingTime; }
    public void setMeetingTime(String meetingTime) { this.meetingTime = meetingTime; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
