package com.campus.marketplace.dto;

public class ExchangeProposalRequest {
    private String pickupSpot;
    private String meetingTime;
    private Double agreedPrice;

    public ExchangeProposalRequest() {}

    public String getPickupSpot() { return pickupSpot; }
    public void setPickupSpot(String pickupSpot) { this.pickupSpot = pickupSpot; }

    public String getMeetingTime() { return meetingTime; }
    public void setMeetingTime(String meetingTime) { this.meetingTime = meetingTime; }

    public Double getAgreedPrice() { return agreedPrice; }
    public void setAgreedPrice(Double agreedPrice) { this.agreedPrice = agreedPrice; }
}
