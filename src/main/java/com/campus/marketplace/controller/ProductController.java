package com.campus.marketplace.controller;

import com.campus.marketplace.dto.ProductRequest;
import com.campus.marketplace.model.Product;
import com.campus.marketplace.model.User;
import com.campus.marketplace.repository.ProductRepository;
import com.campus.marketplace.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String listingType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String query) {
        
        List<Product> results = productRepository.filterProducts(category, listingType, status, query);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Product>> getProductsBySeller(@PathVariable Long sellerId) {
        return ResponseEntity.ok(productRepository.findBySellerId(sellerId));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest req) {
        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setDescription(req.getDescription());
        product.setCategory(req.getCategory());
        product.setListingType(req.getListingType() != null ? req.getListingType() : "FOR_SALE");
        product.setPrice(req.getPrice());
        product.setTradePreferences(req.getTradePreferences());
        product.setCondition(req.getCondition() != null ? req.getCondition() : "GOOD");
        product.setStatus("AVAILABLE");
        product.setImageUrl(req.getImageUrl());
        product.setPreferredPickupSpot(req.getPreferredPickupSpot());
        product.setCreatedAt(LocalDateTime.now());

        if (req.getSellerId() != null) {
            product.setSellerId(req.getSellerId());
            Optional<User> seller = userRepository.findById(req.getSellerId());
            if (seller.isPresent()) {
                product.setSellerName(seller.get().getName());
                product.setSellerEmail(seller.get().getEmail());
                product.setHostelLocation(seller.get().getHostelBlock() + 
                    (seller.get().getRoomNo() != null ? " Rm " + seller.get().getRoomNo() : ""));
            }
        } else {
            product.setSellerName("Anonymous Student");
            product.setHostelLocation(req.getHostelLocation() != null ? req.getHostelLocation() : "Campus");
        }

        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return productRepository.findById(id).map(p -> {
            p.setStatus(status);
            return ResponseEntity.ok(productRepository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
