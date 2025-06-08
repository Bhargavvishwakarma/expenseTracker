package com.bhargav.expenses.service;

import java.security.KeyStore.LoadStoreParameter;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bhargav.expenses.model.Expense;
import com.bhargav.expenses.model.User;
import com.bhargav.expenses.repository.ExpenseRepository;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpenses(String filter, LocalDate startDate, LocalDate endDate){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LocalDate now = LocalDate.now();

        if("week".equals(filter)){
            startDate = now.minusWeeks(1);
            endDate = now;
        }else if("month".equals(filter)){
            startDate = now.minusMonths(1);
            endDate = now;
        }else if ("threeMonths".equals(filter)) {
            startDate = now.minusMonths(3);
            endDate = now;
        }

        return expenseRepository.findByUserAndDateBetween(user, startDate, endDate);

    }

    public Expense updatExpense(Long id, Expense updatedExpense){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Expense expense = expenseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Expense Not Found"));
        if(expense.getUser().getId() != user.getId()){
            throw new RuntimeException("Unauthorized");
        }

        expense.setAmount(updatedExpense.getAmount());
        expense.setCategory(updatedExpense.getCategory());
        expense.setDate(updatedExpense.getDate());
        expense.setDescription(updatedExpense.getDescription());
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Expense expense = expenseRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Expense Not Found"));

        if(expense.getUser().getId() != user.getId()){
            throw new RuntimeException("Unautorized");
        }

        expenseRepository.deleteById(id);
    }

}
