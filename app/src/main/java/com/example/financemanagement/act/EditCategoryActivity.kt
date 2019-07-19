package com.example.financemanagement.act

import android.os.Bundle
import com.example.financemanagement.R
import com.example.financemanagement.bean.Category
import com.example.financemanagement.model.CategoryService
import com.example.financemanagement.common.ts
import kotlinx.android.synthetic.main.activity_edit_category.*

class EditCategoryActivity : BaseActivity() {

    private lateinit var item: Category
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)

        this.item = intent.getSerializableExtra("item") as Category
        this.title = "Edit Category"
        this.txtCategoryName.setText(this.item.CategoryName)
        if (this.item.Type == "Expense") {
            this.rdoExpense.isChecked = true
        } else {
            this.rdoIncome.isChecked = true
        }

        this.btnEdit.setOnClickListener {
            var cate = item.apply {
                CategoryName = this@EditCategoryActivity.txtCategoryName.text.toString()
                Type = if (this@EditCategoryActivity.rdoExpense.isChecked) "Expense" else "Income"
            }
            CategoryService.edit(cate)
            ts("Success")
            this.finish()
        }


    }
}
