package com.accounts.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "accounts")
public class Accounts extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "public_account_number")
    private UUID publicAccountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "account_type")
    private String accountType;
    @Column(name="branch_address")
    private String branchAddress;


    @PrePersist
    public void prePersist() {
            if(this.publicAccountNumber == null) {
                this.publicAccountNumber = Generators.timeBasedEpochGenerator().generate();
            }

         }


}
