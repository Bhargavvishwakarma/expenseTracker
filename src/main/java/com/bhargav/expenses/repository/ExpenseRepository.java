package com.bhargav.expenses.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bhargav.expenses.model.Expense;
import com.bhargav.expenses.model.User;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserAndDateBetween(User user,LocalDate startDate,LocalDate endDate);
    
}
