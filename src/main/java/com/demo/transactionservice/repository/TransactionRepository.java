package com.demo.transactionservice.repository;

import com.demo.transactionservice.model.RecordsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<RecordsTransaction, Long> {
}
