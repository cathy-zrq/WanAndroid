package com.example.financemanagement.act

import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.financemanagement.R
import com.example.financemanagement.bean.Category
import com.example.financemanagement.bean.Finance
import com.example.financemanagement.model.CategoryService
import com.example.financemanagement.model.FinanceService
import com.example.financemanagement.common.ts
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : BaseActivity() {
    private lateinit var item: Finance
    var categoryList = CategoryService.getAll()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        this.item = intent.getSerializableExtra("item") as Finance

        //init
        if (this.item.Type == "Income") {
            this.rdoIncome.isChecked = true
        } else {
            this.rdoExpense.isChecked = true
        }
        this.txtAmount.setText(this.item.Amount.toString())
        this.txtItem.setText(item.Item)
        this.txtTime.text = item.CreateTime

        var selectedType = this.categoryList.singleOrNull { it.CategoryName == this.item.Category }
        if (selectedType != null) {
            this.categoryList.remove(selectedType)
            this.categoryList.add(0, selectedType)
        }
        this.spinnerCategory.adapter =
            ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, categoryList)

        this.title = "Edit"

        this.btnEdit.setOnClickListener {

            if (this.txtAmount.text.toString().trim().length == 0) {
                this.ts("Please input Amount!")
                return@setOnClickListener
            }
            if (this.txtItem.text.toString().trim().length == 0) {
                this.ts("Please input Item!")
                return@setOnClickListener
            }

            if (this.rdoExpense.isChecked == false && this.rdoIncome.isChecked == false) {
                this.ts("Please select Income or Expense!")
                return@setOnClickListener
            }

            if (this.spinnerCategory.adapter.count == 0) {
                this.ts("Please select Category!")
                return@setOnClickListener
            }
            this@EditActivity.item.apply {
                Type = if (this@EditActivity.rdoExpense.isChecked) "Expense" else "Income"
                Category = (this@EditActivity.spinnerCategory.selectedItem as Category).CategoryName
                Amount = this@EditActivity.txtAmount.text.toString().toDouble()
                CreateTime = "${this@EditActivity.txtTime.text}"
                Item = this@EditActivity.txtItem.text.toString()
            }
            FinanceService.edit(this@EditActivity.item)
            this.ts("Success")
            this.finish()
        }


    }
}
