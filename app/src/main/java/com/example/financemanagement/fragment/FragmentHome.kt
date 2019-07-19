package com.example.financemanagement.fragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.financemanagement.act.MainActivity
import com.example.financemanagement.R
import com.example.financemanagement.bean.Finance
import com.example.financemanagement.model.FinanceService
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.view_record.view.*

class FragmentHome : BaseFragment(R.layout.fragment_home) {
    override fun initView(view: View) {
        var all = FinanceService.getAll()

        mView.txtTotalExpense.text = all.filter { it.Type == "Expense" }.sumByDouble { it.Amount }.toString()
        mView.txtTotalIncome.text = all.filter { it.Type == "Income" }.sumByDouble { it.Amount }.toString()


        var dataList = all.takeLast(10).sortedByDescending { it.FinanceId }

        var adapter = object : BaseAdapter() {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                var view = LayoutInflater.from(this@FragmentHome.context).inflate(R.layout.view_record, parent, false)
                var item = getItem(position)

                view.txtType.text = item.Type
                if (item.Type == "Income") {
                    view.txtType.setTextColor(Color.GREEN)
                } else {
                    view.txtType.setTextColor(Color.RED)
                }

                view.txtCategory.text = item.Category
                view.txtAmount.text = item.Amount.toString()
                view.txtCreateTime.text = item.CreateTime
                return view
            }

            override fun getItem(position: Int): Finance {
                return dataList[position]
            }

            override fun getItemId(position: Int): Long {
                return 0
            }

            override fun getCount(): Int {
                return dataList.size
            }
        }

        mView.listLatestRecord.adapter = adapter


        mView.btnInput.setOnClickListener {
         (this.activity as MainActivity).openRecord()

        }

    }


}