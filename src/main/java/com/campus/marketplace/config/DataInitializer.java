package com.campus.marketplace.config;

import com.campus.marketplace.model.*;
import com.campus.marketplace.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final TradeRequestRepository tradeRequestRepository;
    private final TransactionRepository transactionRepository;
    private final ReviewRepository reviewRepository;
    private final ChatMessageRepository chatMessageRepository;

    public DataInitializer(UserRepository userRepository,
                           ProductRepository productRepository,
                           TradeRequestRepository tradeRequestRepository,
                           TransactionRepository transactionRepository,
                           ReviewRepository reviewRepository,
                           ChatMessageRepository chatMessageRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.tradeRequestRepository = tradeRequestRepository;
        this.transactionRepository = transactionRepository;
        this.reviewRepository = reviewRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) return;

        // Seed Users
        User u1 = new User(null, "Alex Rivera", "alex@campus.edu", "22CS0104", 
                "Apex State Institute of Tech", "Hostel Block B", "304", "+1 (555) 234-5678", 
                true, 4.9, 14, "https://api.dicebear.com/7.x/bottts/svg?seed=Alex");
        User u2 = new User(null, "Priya Sharma", "priya@campus.edu", "23BT0215", 
                "Apex State Institute of Tech", "Hostel Block A", "112", "+1 (555) 345-6789", 
                true, 5.0, 9, "https://api.dicebear.com/7.x/bottts/svg?seed=Priya");
        User u3 = new User(null, "Rahul Verma", "rahul@campus.edu", "21ME0089", 
                "Apex State Institute of Tech", "Hostel Block C", "405", "+1 (555) 456-7890", 
                true, 4.8, 21, "https://api.dicebear.com/7.x/bottts/svg?seed=Rahul");
        User u4 = new User(null, "Sarah Jenkins", "sarah@campus.edu", "24EC0330", 
                "Apex State Institute of Tech", "Hostel Block D", "201", "+1 (555) 567-8901", 
                true, 4.9, 5, "https://api.dicebear.com/7.x/bottts/svg?seed=Sarah");

        u1 = userRepository.save(u1);
        u2 = userRepository.save(u2);
        u3 = userRepository.save(u3);
        u4 = userRepository.save(u4);

        // Seed Products: Hostel Essentials
        Product p1 = new Product(null, "Prestige 1.5L Electric Kettle (Clean & Fast Boiling)",
                "Used only 1 semester for making midnight coffee & noodles during exams. Spotless inside with automatic cutoff.",
                "HOSTEL_ESSENTIALS", "FOR_SALE", 14.0, null, "LIKE_NEW", "AVAILABLE",
                "https://images.unsplash.com/photo-1544816155-12df9643f363?auto=format&fit=crop&w=600&q=80",
                u1.getId(), u1.getName(), u1.getEmail(), "Hostel Block B, Rm 304", "Hostel B Gate Quad");

        Product p2 = new Product(null, "Single Bed Orthopedic Foam Mattress (72x36 inch)",
                "Super comfy mattress bought last year. Clean with removable washable zippered cover. Moving out of hostel.",
                "HOSTEL_ESSENTIALS", "FOR_SALE", 35.0, null, "GOOD", "AVAILABLE",
                "https://images.unsplash.com/photo-1584100936595-c0654b55a2e2?auto=format&fit=crop&w=600&q=80",
                u3.getId(), u3.getName(), u3.getEmail(), "Hostel Block C, Rm 405", "Hostel C Ground Floor Lobby");

        Product p3 = new Product(null, "LED Rechargeable Desk Study Lamp (Touch Dimmer)",
                "Battery lasts 6 hours during power cuts. 3 color temperatures (warm, cool, white). Comes with USB-C cable.",
                "HOSTEL_ESSENTIALS", "BOTH", 12.0, "Willing to trade for an extension cord or iron box", "LIKE_NEW", "AVAILABLE",
                "https://images.unsplash.com/photo-1507473885765-e6ed057f782c?auto=format&fit=crop&w=600&q=80",
                u2.getId(), u2.getName(), u2.getEmail(), "Hostel Block A, Rm 112", "Central Library Lawn");

        Product p4 = new Product(null, "Foldable Multi-tier Clothes Drying Rack + Hangers",
                "Folds completely flat under bed when not in use. Essential for rainy season drying. Includes 8 plastic hangers.",
                "HOSTEL_ESSENTIALS", "FOR_SALE", 11.0, null, "GOOD", "AVAILABLE",
                "https://images.unsplash.com/photo-1582735689369-4fe89db7114c?auto=format&fit=crop&w=600&q=80",
                u4.getId(), u4.getName(), u4.getEmail(), "Hostel Block D, Rm 201", "Hostel D Reception");

        // Seed Products: Lab Equipment
        Product p5 = new Product(null, "White Lab Coat (100% Cotton, Size Large)",
                "Required for Chemistry and Biology labs. Washed, ironed, no acid burns or stains. Has 3 deep pockets.",
                "LAB_EQUIPMENT", "BOTH", 10.0, "Trade for Vernier Caliper or Graph Sheet Pad", "LIKE_NEW", "AVAILABLE",
                "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?auto=format&fit=crop&w=600&q=80",
                u2.getId(), u2.getName(), u2.getEmail(), "Hostel Block A, Rm 112", "Chemistry Lab Ground Floor");

        Product p6 = new Product(null, "Engineering Mini Drafter + Waterproof Sheet Holder Tube",
                "Omega mini drafter in perfect alignment with steel clamp. Includes extendable black carrying tube with strap.",
                "LAB_EQUIPMENT", "FOR_TRADE", 0.0, "Looking to trade for Casio 991EX or Engineering Mechanics textbook", "GOOD", "AVAILABLE",
                "https://images.unsplash.com/photo-1581092160607-ee22621dd758?auto=format&fit=crop&w=600&q=80",
                u3.getId(), u3.getName(), u3.getEmail(), "Hostel Block C, Rm 405", "Mechanical Workshop Gate");

        Product p7 = new Product(null, "Vernier Caliper & Micrometer Screw Gauge (0.01mm Precision)",
                "Standard physics lab precision tools. Hard plastic velvet-lined cases included. Smooth thumb roller.",
                "LAB_EQUIPMENT", "FOR_SALE", 15.0, null, "LIKE_NEW", "AVAILABLE",
                "https://images.unsplash.com/photo-1581092335397-9583fe92d232?auto=format&fit=crop&w=600&q=80",
                u1.getId(), u1.getName(), u1.getEmail(), "Hostel Block B, Rm 304", "Physics Department Entrance");

        Product p8 = new Product(null, "Arduino Uno R3 Starter Kit + Sensor Bundle & Jumper Wires",
                "Complete electronics starter kit: ultrasonic sensor, servo motor, LCD 16x2, breadboard, resistors, LEDs.",
                "LAB_EQUIPMENT", "BOTH", 24.0, "Trade for Raspberry Pi Pico or USB Logic Analyzer", "BRAND_NEW", "AVAILABLE",
                "https://images.unsplash.com/photo-1553406830-ef2513450d76?auto=format&fit=crop&w=600&q=80",
                u4.getId(), u4.getName(), u4.getEmail(), "Hostel Block D, Rm 201", "Robotics Club Hub");

        // Seed Products: Textbooks
        Product p9 = new Product(null, "Higher Engineering Mathematics - B.S. Grewal (44th Edition)",
                "Gold standard textbook for 1st & 2nd year calculus, differential equations, and linear algebra. Clean pages, no highlighting.",
                "TEXTBOOKS", "BOTH", 16.0, "Trade for Data Structures or Operating Systems book", "LIKE_NEW", "AVAILABLE",
                "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&w=600&q=80",
                u3.getId(), u3.getName(), u3.getEmail(), "Hostel Block C, Rm 405", "Central Library Entrance Steps");

        Product p10 = new Product(null, "Data Structures & Algorithms in Java (6th Edition) - Goodrich",
                "Hardcover edition with all chapter algorithm walkthroughs. Essential for 3rd semester CS coursework.",
                "TEXTBOOKS", "FOR_SALE", 22.0, null, "GOOD", "AVAILABLE",
                "https://images.unsplash.com/photo-1532012164546-f432f2e3777a?auto=format&fit=crop&w=600&q=80",
                u1.getId(), u1.getName(), u1.getEmail(), "Hostel Block B, Rm 304", "Cafeteria Terrace");

        Product p11 = new Product(null, "University Physics with Modern Physics - Young & Freedman",
                "Massive comprehensive physics textbook for engineering physics 1 & 2. Great diagrams and solved practice problems.",
                "TEXTBOOKS", "FOR_SALE", 28.0, null, "GOOD", "AVAILABLE",
                "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=600&q=80",
                u2.getId(), u2.getName(), u2.getEmail(), "Hostel Block A, Rm 112", "Academic Block 1 Stairs");

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);
        productRepository.save(p6);
        productRepository.save(p7);
        productRepository.save(p8);
        productRepository.save(p9);
        productRepository.save(p10);
        productRepository.save(p11);

        // Seed a sample Trade Request
        TradeRequest trade = new TradeRequest();
        trade.setRequesterId(u1.getId());
        trade.setRequesterName(u1.getName());
        trade.setRequesterEmail(u1.getEmail());
        trade.setTargetProductId(p6.getId());
        trade.setTargetProductTitle(p6.getTitle());
        trade.setTargetSellerId(u3.getId());
        trade.setTargetSellerName(u3.getName());
        trade.setOfferedProductId(p7.getId());
        trade.setOfferedItemTitle("Vernier Caliper & Micrometer Screw Gauge");
        trade.setOfferedItemDescription("Pristine precision tools, ready to swap for your mini drafter for my EG course!");
        trade.setMessage("Hey Rahul, I really need a drafter for ED graphics this sem. Would love to trade my calipers!");
        trade.setStatus("PENDING");
        trade.setPickupSpot("Mechanical Lawn");
        trade.setMeetingTime("Tomorrow 4:30 PM after lecture");
        tradeRequestRepository.save(trade);

        // Seed a sample Chat message
        ChatMessage cm1 = new ChatMessage();
        cm1.setProductId(p1.getId());
        cm1.setSenderId(u2.getId());
        cm1.setSenderName(u2.getName());
        cm1.setReceiverId(u1.getId());
        cm1.setReceiverName(u1.getName());
        cm1.setMessage("Hi Alex! Is the Prestige kettle still available? Can we meet at Hostel B quad?");
        cm1.setMessageType("CHAT");
        chatMessageRepository.save(cm1);

        ChatMessage cm2 = new ChatMessage();
        cm2.setProductId(p1.getId());
        cm2.setSenderId(u1.getId());
        cm2.setSenderName(u1.getName());
        cm2.setReceiverId(u2.getId());
        cm2.setReceiverName(u2.getName());
        cm2.setMessage("Yes Priya, still available! I can come down to the quad around 6 PM today.");
        cm2.setMessageType("PICKUP_PROPOSAL");
        cm2.setProposedLocation("Hostel B Quad");
        chatMessageRepository.save(cm2);

        // Seed a sample Review
        Review r1 = new Review(null, 1L, u2.getId(), u2.getName(), u3.getId(), 5, 
                "Super prompt exchange! Rahul brought the lab manual right on time at the library lawn. Item was as described.");
        Review r2 = new Review(null, 2L, u1.getId(), u1.getName(), u2.getId(), 5, 
                "Smooth trade for my lab apron. Priya was polite, verified college ID on the spot. Highly recommended peer seller!");
        reviewRepository.save(r1);
        reviewRepository.save(r2);
    }
}
