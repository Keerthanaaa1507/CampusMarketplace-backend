package com.campus.marketplace.controller;

import com.campus.marketplace.model.Product;
import com.campus.marketplace.model.Transaction;
import com.campus.marketplace.repository.ProductRepository;
import com.campus.marketplace.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    public TransactionController(TransactionRepository transactionRepository,
                                 ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction tx) {
        tx.setCreatedAt(LocalDateTime.now());
        tx.setStatus("IN_PROGRESS");
        Transaction saved = transactionRepository.save(tx);

        // Update product to RESERVED
        if (tx.getProductId() != null) {
            productRepository.findById(tx.getProductId()).ifPresent(p -> {
                p.setStatus("RESERVED");
                productRepository.save(p);
            });
        }
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionRepository.findByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        return transactionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<?> completeExchange(@PathVariable Long id) {
        Optional<Transaction> opt = transactionRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Transaction tx = opt.get();
        tx.setStatus("COMPLETED");
        tx.setCompletedAt(LocalDateTime.now());
        transactionRepository.save(tx);

        // Update product status to COMPLETED
        if (tx.getProductId() != null) {
            productRepository.findById(tx.getProductId()).ifPresent(p -> {
                p.setStatus("COMPLETED");
                productRepository.save(p);
            });
        }

        return ResponseEntity.ok(tx);
    }
}
