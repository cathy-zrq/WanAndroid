package com.example.financemanagement.fragment

import android.content.Intent
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.financemanagement.act.AddCategoryActivity
import com.example.financemanagement.act.EditCategoryActivity
import com.example.financemanagement.R
import com.example.financemanagement.bean.Category
import com.example.financemanagement.model.CategoryService
import com.example.financemanagement.common.ts
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category.view.*
import kotlinx.android.synthetic.main.view_category.view.*

class FragmentCategory : BaseFragment(R.layout.fragment_category) {
    private var type = "Income"
    private var dataList = arrayListOf<Category>()

    override fun initView(view: View) {

        mView.tabLayout.addTab(mView.tabLayout.newTab().setText("Income"))
        mView.tabLayout.addTab(mView.tabLayout.newTab().setText("Expense"))
        showList()
        mView.tabLayout.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab?> {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    this@FragmentCategory.type = p0.text.toString()
                    showList()
                }
            }
        })

//        mView.rdoIncome.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                this.type = "Income"
//            } else {
//                this.type = "Expense"
//            }
//            showList()
//        }
//
//        mView.rdoExpense.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                this.type = "Expense"
//            } else {
//                this.type = "Income"
//            }
//            showList()
//        }

        //show list
        //   mView.rdoIncome.isChecked = true
        //del
        mView.btnDel.setOnClickListener {
            for (category in this.dataList.filter { it.IsSelected }) {
                this.dataList.remove(category)
                CategoryService.del(category.CategoryId)
            }
            (this.listCategory.adapter as BaseAdapter).notifyDataSetChanged()
            this.context!!.ts("Success")
        }
        mView.btnAdd.setOnClickListener {
            var intent = Intent(this@FragmentCategory.context, AddCategoryActivity::class.java)
            this.startActivity(intent)
        }

    }

    fun showList(): Unit {
        this.dataList.clear()
        this.dataList.addAll(CategoryService.getAll().filter { it.Type == this.type })
        var adapter = object : BaseAdapter() {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                var view =
                    LayoutInflater.from(this@FragmentCategory.context).inflate(R.layout.view_category, parent, false)
                var item = getItem(position)
                view.txtCategory.text = item.CategoryName
                view.ckItem.isChecked = item.IsSelected
                view.ckItem.setOnCheckedChangeListener { buttonView, isChecked ->
                    item.IsSelected = isChecked
                    this.notifyDataSetChanged()
                }
                view.setOnLongClickListener {
                    var intent = Intent(this@FragmentCategory.context, EditCategoryActivity::class.java)
                    intent.putExtra("item", item)
                    this@FragmentCategory.startActivity(intent)
                    return@setOnLongClickListener true
                }
                return view
            }

            override fun getItem(position: Int): Category {
                return dataList[position]
            }

            override fun getItemId(position: Int): Long {
                return 0
            }

            override fun getCount(): Int {
                return dataList.size
            }

        }
        mView.listCategory.adapter = adapter
    }
}