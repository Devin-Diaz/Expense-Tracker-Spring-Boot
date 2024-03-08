package com.diaz.expense_tracker.service;

import com.diaz.expense_tracker.model.Budget;

import java.util.List;

public interface BudgetService {

    List<Budget> findAll();

    void saveBudget(Budget budget);

    void updateBudget(Budget budget);

    void deleteBudget(Long id);

    Budget findById(Long id);



}
