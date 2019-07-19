package com.example.financemanagement.act

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.example.financemanagement.R
import com.example.financemanagement.common.TestDataMaker
import com.example.financemanagement.common.d
import com.example.financemanagement.common.ts
import com.example.financemanagement.fragment.FragmentCategory
import com.example.financemanagement.fragment.FragmentDetail
import com.example.financemanagement.fragment.FragmentHome
import com.example.financemanagement.fragment.FragmentRecord
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = Intent(this, InitActivity::class.java)
        this.startActivity(intent)
        this.hideTitle()
        //init


        //add test data
        var file = File(filesDir, "PutTestData.lock")
        if (!file.exists()) {
            TestDataMaker.mkCategory()
            TestDataMaker.mkItem()
            file.createNewFile()
            file.writeBytes("OK".toByteArray())
            "MKTestData".d()
        }
        openFragment(FragmentHome())

        this.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btnHome -> {
                    this.openHome()
                }
                R.id.btnRecord -> {
                    this.openRecord()
                }
                R.id.btnDetail -> {
                    this.openDetail()
                }
                R.id.btnCategory -> {
                    this.openCategory()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }


    }

    /**
     * open a fragment
     */
    fun openFragment(fragment: Fragment): Unit {
        var tran = supportFragmentManager.beginTransaction()
        tran.replace(R.id.frameLayout, fragment).commit()
    }

    //open home
    fun openHome(): Unit {
        this.txtTitle.text = "Finance Management System"
        openFragment(FragmentHome())
    }

    fun openRecord(): Unit {
        this.txtTitle.text = "Input a record"
        openFragment(FragmentRecord())
    }

    fun openDetail(): Unit {
        this.txtTitle.text = "Detail"
        openFragment(FragmentDetail())
    }

    fun openCategory(): Unit {
        this.txtTitle.text = "Category"
        openFragment(FragmentCategory())
    }


}
