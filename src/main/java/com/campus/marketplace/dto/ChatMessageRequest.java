package com.campus.marketplace.dto;

public class ChatMessageRequest {
    private Long productId;
    private Long senderId;
    private Long receiverId;
    private String message;
    private String messageType; // CHAT, OFFER, PICKUP_PROPOSAL
    private Double offeredPrice;
    private String proposedLocation;

    public ChatMessageRequest() {}

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public Long getReceiverId() { return receiverId; }
    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }

    public Double getOfferedPrice() { return offeredPrice; }
    public void setOfferedPrice(Double offeredPrice) { this.offeredPrice = offeredPrice; }

    public String getProposedLocation() { return proposedLocation; }
    public void setProposedLocation(String proposedLocation) { this.proposedLocation = proposedLocation; }
}
