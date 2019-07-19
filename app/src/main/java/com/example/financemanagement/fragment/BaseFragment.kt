package com.example.financemanagement.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.financemanagement.act.MainActivity

@SuppressLint("ValidFragment")
abstract class BaseFragment(var layoutId: Int) : Fragment() {
    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.mView = inflater.inflate(layoutId, container, false)
        initView(mView)
        return this.mView
    }

    abstract fun initView(view: View)


    fun goHome(): Unit {
        (this.activity as MainActivity).openHome()
    }

}