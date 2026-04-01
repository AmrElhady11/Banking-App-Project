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
    private UUID accountNumber;


    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "account_type")
    private String accountType;
    @Column(name="branch_address")
    private String branchAddress;


    @PrePersist
    public void prePersist() {
            if(this.accountNumber == null) {
                this.accountNumber = Generators.timeBasedEpochGenerator().generate();
            }

         }


}
