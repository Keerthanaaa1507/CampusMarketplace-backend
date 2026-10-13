package com.campus.marketplace.controller;

import com.campus.marketplace.dto.LoginRequest;
import com.campus.marketplace.dto.RegisterRequest;
import com.campus.marketplace.model.User;
import com.campus.marketplace.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (req.getEmail() == null || req.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Campus email is required");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body("An account with this campus email already exists");
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setCollegeId(req.getCollegeId());
        user.setCollegeName(req.getCollegeName() != null ? req.getCollegeName() : "Campus University");
        user.setHostelBlock(req.getHostelBlock());
        user.setRoomNo(req.getRoomNo());
        user.setPhone(req.getPhone());
        user.setVerified(true); // Auto-verify upon entering valid college credentials
        user.setRating(5.0);
        user.setReviewCount(0);
        user.setAvatar("https://api.dicebear.com/7.x/bottts/svg?seed=" + req.getName());

        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        if (req.getEmail() == null) {
            return ResponseEntity.badRequest().body("Email required");
        }
        Optional<User> userOpt = userRepository.findByEmail(req.getEmail().trim());
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }
        return ResponseEntity.status(404).body("User not found with email: " + req.getEmail());
    }

    @PostMapping("/verify/{id}")
    public ResponseEntity<?> verifyUser(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            user.setVerified(true);
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
