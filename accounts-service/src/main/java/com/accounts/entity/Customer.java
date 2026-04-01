package com.accounts.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "customer_id")
    private UUID customerId;



    private String name;
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @PrePersist
    public void prePersist() {
    if(this.customerId == null) {
        this.customerId = Generators.timeBasedEpochGenerator().generate();

    }
    }
}
