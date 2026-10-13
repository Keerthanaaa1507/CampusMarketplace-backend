package com.campus.marketplace.repository;

import com.campus.marketplace.model.TradeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRequestRepository extends JpaRepository<TradeRequest, Long> {
    List<TradeRequest> findByTargetSellerId(Long targetSellerId);
    List<TradeRequest> findByRequesterId(Long requesterId);
    List<TradeRequest> findByTargetProductId(Long targetProductId);
}
