package com.example.isa.repository;

import com.example.isa.model.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {
}
