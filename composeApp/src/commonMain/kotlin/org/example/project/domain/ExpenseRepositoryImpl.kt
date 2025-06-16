package org.example.project.domain

import org.example.project.data.ExpenseManager
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

class ExpenseRepositoryImpl: ExpenseRepository {
    override fun getExpenseList(): List<Expense> {
        return ExpenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        ExpenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        ExpenseManager.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return ExpenseManager.getCategories()
    }
}