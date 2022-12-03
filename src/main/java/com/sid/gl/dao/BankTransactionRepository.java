package com.sid.gl.dao;

import com.sid.gl.model.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransactionRepository
extends JpaRepository<BankTransaction,Long> {
}
