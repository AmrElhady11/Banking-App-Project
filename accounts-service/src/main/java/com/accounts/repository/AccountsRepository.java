package com.accounts.repository;

import com.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    Optional<Accounts> findByPublicAccountNumber(UUID publicAccountNumber);
}
