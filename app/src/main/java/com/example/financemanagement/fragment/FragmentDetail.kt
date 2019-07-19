package com.example.financemanagement.fragment

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import com.example.financemanagement.act.EditActivity
import com.example.financemanagement.R
import com.example.financemanagement.bean.Finance
import com.example.financemanagement.common.ts
import com.example.financemanagement.model.FinanceService
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.view_detail.view.*
import kotlinx.android.synthetic.main.view_operation.view.*

class FragmentDetail : BaseFragment(R.layout.fragment_detail) {
    var dataList = FinanceService.getAll()
    var tempData = arrayListOf<Finance>()
    lateinit var adapter: BaseAdapter
    var index = 1
    override fun initView(view: View) {
        //get first 5
        for (finance in this.dataList.take(5)) {
            this.tempData.add(finance)
        }
        //mk adapter
        this.adapter = object : BaseAdapter() {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                var item = getItem(position)
                var view = LayoutInflater.from(this@FragmentDetail.context).inflate(R.layout.view_detail, parent, false)
                view.txtTime.text = item.CreateTime
                view.txtAmount.setText(item.Amount.toString())
                view.txtItem.setText("${item.Item}(${item.Category})")
                view.txtType.text = "[${item.Type}]"
                if (item.Type == "Income") {
                    view.txtType.setTextColor(Color.GREEN)
                } else {
                    view.txtType.setTextColor(Color.RED)
                }

                //edit and delete
                view.setOnLongClickListener {
                    var view = View.inflate(this@FragmentDetail.context!!, R.layout.view_operation, null)
                    var dialog = AlertDialog.Builder(this@FragmentDetail.context!!)
                        .setTitle("Select a operation")
                        .setView(view)
                        .create()
                    //del
                    view.btnDel.setOnClickListener {
                        dialog.dismiss()
                        AlertDialog.Builder(this@FragmentDetail.context!!)
                            .setTitle("Are you sure to delete?")
                            .setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                }
                            })
                            .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    FinanceService.del(item.FinanceId)
                                    this@FragmentDetail.tempData.remove(item)
                                    this@FragmentDetail.adapter.notifyDataSetChanged()
                                }
                            }).create().show()
                    }
                    //edit
                    view.btnEdit.setOnClickListener {
                        dialog.dismiss()
                        goHome()
                        var intent = Intent(this@FragmentDetail.context, EditActivity::class.java)
                        intent.putExtra("item", item)
                        this@FragmentDetail.startActivity(intent)
                    }
                    //cancel
                    view.btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                    return@setOnLongClickListener true
                }
                return view
            }

            override fun getItem(position: Int): Finance {
                return this@FragmentDetail.tempData[position]
            }

            override fun getItemId(position: Int): Long {
                return 0
            }

            override fun getCount(): Int {
                return this@FragmentDetail.tempData.size
            }
        }

        mView.listViewDetail.adapter = this.adapter


        //load more

        mView.listViewDetail.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {

            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (mView.listViewDetail.lastVisiblePosition == mView.listViewDetail.count - 1) {
                        nextPage()
                    }
                }
            }

        })
    }

    fun nextPage(): Unit {
        if (this.index * 5 >= this.dataList.size) {
            this.context!!.ts("At the end!")
            return
        }
        this.index++
        var tm = dataList.drop((this.index - 1) * 5).take(5)
        this.tempData.addAll(tm)
        this.adapter.notifyDataSetChanged()
    }
}