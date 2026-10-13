package com.campus.marketplace.repository;

import com.campus.marketplace.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTargetUserIdOrderByCreatedAtDesc(Long targetUserId);
    List<Review> findByTransactionId(Long transactionId);
}
