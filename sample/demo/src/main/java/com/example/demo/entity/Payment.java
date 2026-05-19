package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long paymentId;

    private Long status;

    private Double amount;

    @OneToOne
    @JoinColumn(name = "RequestId")
    private Request request;
}
