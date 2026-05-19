package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;
    private String status;

    @ManyToOne
    @JoinColumn(name = "SenderId")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "ReceiverId")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "SkillId")
    private Skill skill;

    @OneToOne(mappedBy = "request")
    private Review review;

    @OneToOne(mappedBy = "request")
    private Payment payment;
}
