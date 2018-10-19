package com.in28minutes.springboot.rest.example.gamestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.FileType;

@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {
	Optional<FileType> findByFileTypeAndContentType(String fileType, String contentType);
}
