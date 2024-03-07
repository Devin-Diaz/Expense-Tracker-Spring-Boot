package com.diaz.expense_tracker.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budget table")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total budget")
    private BigDecimal totalBudgetAmount;

    @ManyToOne
    @JoinColumn("user_id")
    private User user;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private Set<Expense> expenses;


    public BigDecimal getRemainingBudget() {
        return totalBudgetAmount.subtract(getAmountSpent());
    }

    public BigDecimal getAmountSpent() {
        BigDecimal total = BigDecimal.ZERO;

        for(Expense expense : expenses) {
            total = total.add(expense.getAmount());
        }
        return total;
    }


}
