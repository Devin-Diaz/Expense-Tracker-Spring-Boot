package com.diaz.expense_tracker.service;

import com.diaz.expense_tracker.model.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> findAll();

    void saveExpense(Expense expense);

    void updateExpense(Expense expense);

    void delete(Long id);

    Expense findById(Long id);

}
