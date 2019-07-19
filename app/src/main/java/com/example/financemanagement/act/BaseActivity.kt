package com.example.financemanagement.act

import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {


    fun hideTitle(): Unit {
        this.supportActionBar!!.hide()
    }

}