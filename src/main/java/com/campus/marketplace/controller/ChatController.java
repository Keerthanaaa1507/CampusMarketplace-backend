package com.campus.marketplace.controller;

import com.campus.marketplace.dto.ChatMessageRequest;
import com.campus.marketplace.model.ChatMessage;
import com.campus.marketplace.model.User;
import com.campus.marketplace.repository.ChatMessageRepository;
import com.campus.marketplace.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public ChatController(ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ChatMessage>> getProductMessages(@PathVariable Long productId) {
        return ResponseEntity.ok(chatMessageRepository.findByProductIdOrderByTimestampAsc(productId));
    }

    @PostMapping
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody ChatMessageRequest req) {
        ChatMessage msg = new ChatMessage();
        msg.setProductId(req.getProductId());
        msg.setSenderId(req.getSenderId());
        msg.setReceiverId(req.getReceiverId());
        msg.setMessage(req.getMessage());
        msg.setMessageType(req.getMessageType() != null ? req.getMessageType() : "CHAT");
        msg.setOfferedPrice(req.getOfferedPrice());
        msg.setProposedLocation(req.getProposedLocation());
        msg.setTimestamp(LocalDateTime.now());

        if (req.getSenderId() != null) {
            Optional<User> sender = userRepository.findById(req.getSenderId());
            sender.ifPresent(u -> msg.setSenderName(u.getName()));
        }
        if (req.getReceiverId() != null) {
            Optional<User> receiver = userRepository.findById(req.getReceiverId());
            receiver.ifPresent(u -> msg.setReceiverName(u.getName()));
        }

        ChatMessage saved = chatMessageRepository.save(msg);
        return ResponseEntity.ok(saved);
    }
}
