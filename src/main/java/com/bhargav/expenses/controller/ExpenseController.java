package com.bhargav.expenses.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhargav.expenses.model.Expense;
import com.bhargav.expenses.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){
        return ResponseEntity.status(201).body(expenseService.createExpense(expense));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses(
        @RequestParam(required = false) String filter,
        @RequestParam(required = false) LocalDate startDate,
        @RequestParam(required = false) LocalDate endDate){
            return ResponseEntity.status(201).body(expenseService.getExpenses(filter, startDate, endDate));
        }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,@RequestBody Expense expense){
        return ResponseEntity.status(201).body(expenseService.updatExpense(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
