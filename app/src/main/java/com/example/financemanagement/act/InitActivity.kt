package com.example.financemanagement.act

import android.os.Bundle
import com.example.financemanagement.R
import kotlinx.android.synthetic.main.activity_init.*
import kotlin.concurrent.thread

class InitActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        this.hideTitle()


        thread {
            var index = 5
            while (true) {
                runOnUiThread {
                    this.txtSkip.text = "${index}S Skip"
                }
                Thread.sleep(1000)
                index--
                if (index == 0) {
                    this.finish()
                }
            }
        }

        this.txtSkip.setOnClickListener {
            this.finish()
        }


    }
}
