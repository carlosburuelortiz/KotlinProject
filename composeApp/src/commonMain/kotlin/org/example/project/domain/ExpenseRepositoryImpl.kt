package org.example.project.domain

import org.example.project.data.ExpenseManager
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

class ExpenseRepositoryImpl(private val expenseManager: ExpenseManager): ExpenseRepository {
    override fun getAllExpenses(): List<Expense> {
        return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManager.getCategories()
    }
}