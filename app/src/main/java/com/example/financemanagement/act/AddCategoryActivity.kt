package com.example.financemanagement.act

import android.os.Bundle
import com.example.financemanagement.R
import com.example.financemanagement.bean.Category
import com.example.financemanagement.model.CategoryService
import com.example.financemanagement.common.ts
import kotlinx.android.synthetic.main.activity_add_category.*

class AddCategoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        this.title = "Add Category"

        this.btnAdd.setOnClickListener {
            var cate = Category().apply {
                CategoryName = this@AddCategoryActivity.txtCategoryName.text.toString()
                Type = if (this@AddCategoryActivity.rdoExpense.isChecked) "Expense" else "Income"
            }
            CategoryService.add(cate)
            ts("Success")
            this.finish()
        }


    }
}
