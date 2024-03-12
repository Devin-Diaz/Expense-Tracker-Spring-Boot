package com.diaz.expense_tracker.service_impl;

import com.diaz.expense_tracker.Repository.ExpenseRepository;
import com.diaz.expense_tracker.model.Expense;
import com.diaz.expense_tracker.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        Expense existingExpense = expenseRepository.findById(expense.getId()).
                orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + expense.getId()));

        updateExpenseHelper(existingExpense, expense);

        expenseRepository.save(existingExpense);
    }

    private void updateExpenseHelper(Expense existingExpense, Expense updatedExpense) {
        existingExpense.setExpenseName(updatedExpense.getExpenseName());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setDescription(updatedExpense.getDescription());
    }

    @Override
    public void delete(Long id) {
        Expense expense = findById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense findById(Long id) {
        if(expenseRepository.findById(id).isPresent())
            return expenseRepository.findById(id).get();

        return null;
    }

}
