package com.example.financemanagement.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.financemanagement.R
import com.example.financemanagement.bean.Category
import com.example.financemanagement.bean.Finance
import com.example.financemanagement.model.CategoryService
import com.example.financemanagement.model.FinanceService
import com.example.financemanagement.common.serToString
import com.example.financemanagement.common.ts
import kotlinx.android.synthetic.main.fragment_input.view.*
import java.util.*

class FragmentRecord : BaseFragment(R.layout.fragment_input) {
    var categoryList = CategoryService.getAll()
    var calendar = Calendar.getInstance()
    override fun initView(view: View) {
        mView.txtTime.text = this.calendar.time.serToString("yyyy-MM-dd HH:mm")
        //show category
        mView.spinnerCategory.adapter =
            ArrayAdapter<Category>(this.context, android.R.layout.simple_list_item_1, categoryList)
        //select date time
        mView.txtTime.setOnClickListener {
            var datePickerDialog = DatePickerDialog(
                this.context,
                object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        this@FragmentRecord.calendar.set(year, month, dayOfMonth)
                        var dialog = TimePickerDialog(
                            this@FragmentRecord.context, object : TimePickerDialog.OnTimeSetListener {
                                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                                    this@FragmentRecord.calendar.set(year, month, dayOfMonth, hourOfDay, minute)
                                    mView.txtTime.text =
                                        this@FragmentRecord.calendar.time.serToString("yyyy-MM-dd HH:mm")
                                }
                            }, this@FragmentRecord.calendar.get(Calendar.HOUR),
                            this@FragmentRecord.calendar.get(Calendar.MINUTE), true
                        ).show()
                    }
                },
                this.calendar.get(Calendar.YEAR),
                this.calendar.get(Calendar.MONTH),
                this.calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        mView.btnSave.setOnClickListener {

            if (mView.txtAmount.text.toString().trim().length == 0) {
                this.context!!.ts("Please input Amount!")
                return@setOnClickListener
            }
            if (mView.txtItem.text.toString().trim().length == 0) {
                this.context!!.ts("Please input Item!")
                return@setOnClickListener
            }

            if (mView.rdoExpense.isChecked == false && mView.rdoIncome.isChecked == false) {
                this.context!!.ts("Please select Income or Expense!")
                return@setOnClickListener
            }

            if (mView.spinnerCategory.adapter.count == 0) {
                this.context!!.ts("Please select Category!")
                return@setOnClickListener
            }

            FinanceService.add(Finance().apply {
                Type = if (mView.rdoExpense.isChecked) "Expense" else "Income"
                Category = (mView.spinnerCategory.selectedItem as Category).CategoryName
                Amount = mView.txtAmount.text.toString().toDouble()
                CreateTime = "${mView.txtTime.text}"
                Item = mView.txtItem.text.toString()
            })
            this.context!!.ts("Success")
            goHome()
        }

    }
}