package com.nt.service;



import java.util.List;


import org.springframework.stereotype.Service;

import com.nt.entity.Transaction;
import com.nt.entity.User;
import com.nt.repo.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findTransactionsByUser(User user) {
        return transactionRepository.findAll();
    }
}
