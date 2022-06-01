package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.AccountDeletionRequest;

import java.util.List;

public interface DeletionRequestRepository extends JpaRepository<AccountDeletionRequest, Long> {
    public List<AccountDeletionRequest> findAllByClientId(Long id);

}
