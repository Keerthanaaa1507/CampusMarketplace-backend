package com.campus.marketplace.dto;

public class TradeOfferRequest {
    private Long requesterId;
    private Long targetProductId;
    private Long offeredProductId;
    private String offeredItemTitle;
    private String offeredItemDescription;
    private String message;

    public TradeOfferRequest() {}

    public Long getRequesterId() { return requesterId; }
    public void setRequesterId(Long requesterId) { this.requesterId = requesterId; }

    public Long getTargetProductId() { return targetProductId; }
    public void setTargetProductId(Long targetProductId) { this.targetProductId = targetProductId; }

    public Long getOfferedProductId() { return offeredProductId; }
    public void setOfferedProductId(Long offeredProductId) { this.offeredProductId = offeredProductId; }

    public String getOfferedItemTitle() { return offeredItemTitle; }
    public void setOfferedItemTitle(String offeredItemTitle) { this.offeredItemTitle = offeredItemTitle; }

    public String getOfferedItemDescription() { return offeredItemDescription; }
    public void setOfferedItemDescription(String offeredItemDescription) { this.offeredItemDescription = offeredItemDescription; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
