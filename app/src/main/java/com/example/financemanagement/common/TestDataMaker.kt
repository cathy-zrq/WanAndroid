package com.example.financemanagement.common

import com.example.financemanagement.bean.Category
import com.example.financemanagement.bean.Finance
import com.example.financemanagement.model.CategoryService
import com.example.financemanagement.model.FinanceService

class TestDataMaker {
    companion object {
        fun mkItem(): Unit {
            for (i in 0..20) {
                FinanceService.add(Finance().apply {
                    Type = "Expense"
                    Item = "Item" + i.toString()
                    Category = "TESTIncome1"
                    Amount = getRandom(10, 100).toDouble()
                    CreateTime = "2001-04-05"
                })
                FinanceService.add(Finance().apply {
                    Type = "Income"
                    Item = "Item" + i.toString()
                    Category = "TESTIncome1"
                    Amount = getRandom(10, 100).toDouble()
                    CreateTime = "2001-04-05"
                })
            }
        }

        fun mkCategory(): Unit {
            for (i in 0..5) {
                CategoryService.add(Category().apply {
                    CategoryName = "TESTExpense" + i.toString()
                    Type = "Expense"
                })
                CategoryService.add(Category().apply {
                    CategoryName = "TESTIncome" + i.toString()
                    Type = "Income"
                })
            }
        }
    }

}