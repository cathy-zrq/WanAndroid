package com.example.financemanagement.common

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


//---common data val
var mContext by Delegates.notNull<Context>()


fun Context.ts(msg: String): Unit {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun String.d(TAG: String = "TAG"): Unit {
    Log.d(TAG, this)
}


fun getRandom(min: Int, max: Int): Int {
    return Random().nextInt(max - min + 1) + min
}


fun Date.serToString(format: String): String {
    return SimpleDateFormat(format).format(this)
}