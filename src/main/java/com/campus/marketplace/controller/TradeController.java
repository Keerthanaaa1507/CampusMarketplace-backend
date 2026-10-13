package com.campus.marketplace.controller;

import com.campus.marketplace.dto.ExchangeProposalRequest;
import com.campus.marketplace.dto.TradeOfferRequest;
import com.campus.marketplace.model.Product;
import com.campus.marketplace.model.TradeRequest;
import com.campus.marketplace.model.Transaction;
import com.campus.marketplace.model.User;
import com.campus.marketplace.repository.ProductRepository;
import com.campus.marketplace.repository.TradeRequestRepository;
import com.campus.marketplace.repository.TransactionRepository;
import com.campus.marketplace.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trades")
@CrossOrigin(origins = "*")
public class TradeController {

    private final TradeRequestRepository tradeRequestRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public TradeController(TradeRequestRepository tradeRequestRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository,
                           TransactionRepository transactionRepository) {
        this.tradeRequestRepository = tradeRequestRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    public ResponseEntity<?> sendTradeRequest(@RequestBody TradeOfferRequest req) {
        Optional<Product> targetProd = productRepository.findById(req.getTargetProductId());
        if (!targetProd.isPresent()) {
            return ResponseEntity.badRequest().body("Target product not found");
        }

        Optional<User> requester = userRepository.findById(req.getRequesterId());
        if (!requester.isPresent()) {
            return ResponseEntity.badRequest().body("Requester not found");
        }

        Product target = targetProd.get();
        User reqUser = requester.get();

        TradeRequest trade = new TradeRequest();
        trade.setRequesterId(reqUser.getId());
        trade.setRequesterName(reqUser.getName());
        trade.setRequesterEmail(reqUser.getEmail());

        trade.setTargetProductId(target.getId());
        trade.setTargetProductTitle(target.getTitle());
        trade.setTargetSellerId(target.getSellerId());
        trade.setTargetSellerName(target.getSellerName());

        trade.setOfferedProductId(req.getOfferedProductId());
        trade.setOfferedItemTitle(req.getOfferedItemTitle());
        trade.setOfferedItemDescription(req.getOfferedItemDescription());
        trade.setMessage(req.getMessage());
        trade.setStatus("PENDING");
        trade.setCreatedAt(LocalDateTime.now());
        trade.setUpdatedAt(LocalDateTime.now());

        TradeRequest saved = tradeRequestRepository.save(trade);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/incoming/{userId}")
    public ResponseEntity<List<TradeRequest>> getIncomingTrades(@PathVariable Long userId) {
        return ResponseEntity.ok(tradeRequestRepository.findByTargetSellerId(userId));
    }

    @GetMapping("/outgoing/{userId}")
    public ResponseEntity<List<TradeRequest>> getOutgoingTrades(@PathVariable Long userId) {
        return ResponseEntity.ok(tradeRequestRepository.findByRequesterId(userId));
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<?> acceptTrade(@PathVariable Long id) {
        Optional<TradeRequest> opt = tradeRequestRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TradeRequest trade = opt.get();
        trade.setStatus("ACCEPTED");
        trade.setUpdatedAt(LocalDateTime.now());
        tradeRequestRepository.save(trade);

        // Mark target product as RESERVED
        productRepository.findById(trade.getTargetProductId()).ifPresent(p -> {
            p.setStatus("RESERVED");
            productRepository.save(p);
        });

        // Also if offered item exists, mark it as RESERVED
        if (trade.getOfferedProductId() != null) {
            productRepository.findById(trade.getOfferedProductId()).ifPresent(p -> {
                p.setStatus("RESERVED");
                productRepository.save(p);
            });
        }

        // Create an in-progress transaction
        Transaction tx = new Transaction();
        tx.setProductId(trade.getTargetProductId());
        tx.setProductTitle(trade.getTargetProductTitle() + " <-> " + trade.getOfferedItemTitle());
        tx.setBuyerId(trade.getRequesterId());
        tx.setBuyerName(trade.getRequesterName());
        tx.setSellerId(trade.getTargetSellerId());
        tx.setSellerName(trade.getTargetSellerName());
        tx.setTransactionType("TRADE");
        tx.setAgreedPrice(0.0);
        tx.setStatus("IN_PROGRESS");
        tx.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(tx);

        return ResponseEntity.ok(trade);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<?> rejectTrade(@PathVariable Long id) {
        Optional<TradeRequest> opt = tradeRequestRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TradeRequest trade = opt.get();
        trade.setStatus("REJECTED");
        trade.setUpdatedAt(LocalDateTime.now());
        tradeRequestRepository.save(trade);
        return ResponseEntity.ok(trade);
    }

    @PostMapping("/{id}/arrange")
    public ResponseEntity<?> arrangeExchange(@PathVariable Long id, @RequestBody ExchangeProposalRequest req) {
        Optional<TradeRequest> opt = tradeRequestRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TradeRequest trade = opt.get();
        trade.setPickupSpot(req.getPickupSpot());
        trade.setMeetingTime(req.getMeetingTime());
        trade.setUpdatedAt(LocalDateTime.now());
        tradeRequestRepository.save(trade);
        return ResponseEntity.ok(trade);
    }
}
