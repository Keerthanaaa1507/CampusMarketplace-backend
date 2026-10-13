package com.campus.marketplace.controller;

import com.campus.marketplace.dto.ReviewRequest;
import com.campus.marketplace.model.Review;
import com.campus.marketplace.model.Transaction;
import com.campus.marketplace.model.User;
import com.campus.marketplace.repository.ReviewRepository;
import com.campus.marketplace.repository.TransactionRepository;
import com.campus.marketplace.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            UserRepository userRepository,
                            TransactionRepository transactionRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    public ResponseEntity<?> submitReview(@RequestBody ReviewRequest req) {
        if (req.getRating() < 1 || req.getRating() > 5) {
            return ResponseEntity.badRequest().body("Rating must be between 1 and 5");
        }

        Optional<User> reviewerOpt = userRepository.findById(req.getReviewerId());
        Optional<User> targetUserOpt = userRepository.findById(req.getTargetUserId());

        if (!reviewerOpt.isPresent() || !targetUserOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Reviewer or target user not found");
        }

        Review review = new Review();
        review.setTransactionId(req.getTransactionId());
        review.setReviewerId(req.getReviewerId());
        review.setReviewerName(reviewerOpt.get().getName());
        review.setTargetUserId(req.getTargetUserId());
        review.setRating(req.getRating());
        review.setComment(req.getComment());
        review.setCreatedAt(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);

        // Update target user's average rating
        User targetUser = targetUserOpt.get();
        List<Review> allReviews = reviewRepository.findByTargetUserIdOrderByCreatedAtDesc(targetUser.getId());
        double avg = allReviews.stream().mapToInt(Review::getRating).average().orElse(5.0);
        targetUser.setRating(Math.round(avg * 10.0) / 10.0);
        targetUser.setReviewCount(allReviews.size());
        userRepository.save(targetUser);

        // Mark transaction reviewed
        if (req.getTransactionId() != null) {
            transactionRepository.findById(req.getTransactionId()).ifPresent(tx -> {
                if (req.getReviewerId().equals(tx.getBuyerId())) {
                    tx.setBuyerReviewed(true);
                } else {
                    tx.setSellerReviewed(true);
                }
                transactionRepository.save(tx);
            });
        }

        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewRepository.findByTargetUserIdOrderByCreatedAtDesc(userId));
    }
}
