package com.campus.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String collegeId; // e.g. "21BCE1045"

    private String collegeName; // e.g. "National Institute of Tech"

    private String hostelBlock; // e.g. "Hostel Block B"

    private String roomNo; // e.g. "304"

    private String phone;

    private boolean verified;

    private double rating = 5.0;

    private int reviewCount = 0;

    private String avatar;

    public User() {}

    public User(Long id, String name, String email, String collegeId, String collegeName, 
                String hostelBlock, String roomNo, String phone, boolean verified, 
                double rating, int reviewCount, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.hostelBlock = hostelBlock;
        this.roomNo = roomNo;
        this.phone = phone;
        this.verified = verified;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.avatar = avatar;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCollegeId() { return collegeId; }
    public void setCollegeId(String collegeId) { this.collegeId = collegeId; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getHostelBlock() { return hostelBlock; }
    public void setHostelBlock(String hostelBlock) { this.hostelBlock = hostelBlock; }

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
}
