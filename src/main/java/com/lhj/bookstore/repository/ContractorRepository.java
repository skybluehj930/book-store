package com.lhj.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lhj.bookstore.entity.ContractorEntity;

public interface ContractorRepository extends JpaRepository <ContractorEntity, Long> {

}
