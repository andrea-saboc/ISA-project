package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
	
}
