package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class AppService {

    private final UserRepo userRepo;
    private final SkillRepo skillRepo;
    private final RequestRepo requestRepo;
    private final PaymentRepo paymentRepo;
    private final ReviewRepo reviewRepo;

    public AppService(
            UserRepo userRepo,
            SkillRepo skillRepo,
            RequestRepo requestRepo,
            PaymentRepo paymentRepo,
            ReviewRepo reviewRepo) {

        this.userRepo = userRepo;
        this.skillRepo = skillRepo;
        this.requestRepo = requestRepo;
        this.paymentRepo = paymentRepo;
        this.reviewRepo = reviewRepo;
    }


    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Skill createSkill(Long userId, Skill skill) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        skill.setUser(user);
        return skillRepo.save(skill);
    }

    public Skill getSkillByName(String name) {
        return skillRepo.findAll().stream()
                .filter(skill -> skill.getSkillName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public Request createRequest(Long SenderId, Long ReceiverId, Long SkillId) {
        User sender = userRepo.findById(SenderId).orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepo.findById(ReceiverId).orElseThrow(() -> new RuntimeException("Receiver not found"));
        Skill skill = skillRepo.findById(SkillId).orElseThrow(() -> new RuntimeException("Skill not found"));

        Request request = new Request();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setSkill(skill);
        request.setStatus("Pending");

        return requestRepo.save(request);
    }

    public Request AcceptRequest(Long requestId) {
        Request request = requestRepo.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("Accepted");
        return requestRepo.save(request);
    }

    public Request RejectRequest(Long requestId) {
        Request request = requestRepo.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("Rejected");
        return requestRepo.save(request);
    }
    public Payment createPayment(Long requestId, Double amount) {

        Request request = requestRepo.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));

        if(!request.getStatus().equals("Accepted")) {
            throw new RuntimeException("Request must be accepted before making a payment");
        }

        Payment payment = new Payment();
        payment.setRequest(request);
        payment.setAmount(amount);

        return paymentRepo.save(payment);
    }
    
    public Review createReview(Long requestId, String comment, int rating) {
        Request request = requestRepo.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        if (!"Accepted".equals(request.getStatus())) {
            throw new RuntimeException("Request must be accepted before leaving a review");
        }
        Review review = new Review();
        review.setRequest(request);
        review.setComment(comment);
        review.setRating(rating);
        return reviewRepo.save(review);
    }

}
