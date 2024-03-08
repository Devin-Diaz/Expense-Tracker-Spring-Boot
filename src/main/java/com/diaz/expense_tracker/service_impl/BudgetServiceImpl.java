package com.diaz.expense_tracker.impl;

import com.diaz.expense_tracker.Repository.BudgetRepository;
import com.diaz.expense_tracker.model.Budget;
import com.diaz.expense_tracker.service.BudgetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    @Override
    public void saveBudget(Budget budget) {
        budgetRepository.save(budget);
    }

    @Override
    public void updateBudget(Budget budget) {
        Budget existingBudget = budgetRepository.findById(budget.getId()).
                orElseThrow(() -> new EntityNotFoundException("Budget not found with id: " + budget.getId()));

        updateBudgetHelper(existingBudget, budget);
        budgetRepository.save(existingBudget);
    }

    private void updateBudgetHelper(Budget existingBudget, Budget newBudget) {
        existingBudget.setTotalBudgetAmount(newBudget.getTotalBudgetAmount());
    }

    @Override
    public void deleteBudget(Long id) {
        Budget budget = findById(id);
        budgetRepository.delete(budget);
    }

    @Override
    public Budget findById(Long id) {
        if(budgetRepository.findById(id).isPresent())
            return budgetRepository.findById(id).get();

        return null;
    }
}
