package com.campus.marketplace.repository;

import com.campus.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySellerId(Long sellerId);

    List<Product> findByCategory(String category);

    List<Product> findByListingType(String listingType);

    List<Product> findByStatus(String status);

    @Query("SELECT p FROM Product p WHERE " +
           "(:category IS NULL OR :category = '' OR p.category = :category) AND " +
           "(:listingType IS NULL OR :listingType = '' OR p.listingType = :listingType OR p.listingType = 'BOTH') AND " +
           "(:status IS NULL OR :status = '' OR p.status = :status) AND " +
           "(:query IS NULL OR :query = '' OR LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Product> filterProducts(@Param("category") String category,
                                 @Param("listingType") String listingType,
                                 @Param("status") String status,
                                 @Param("query") String query);
}
