package com.diaz.expense_tracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expense_table")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_name")
    private String expenseName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;


}
