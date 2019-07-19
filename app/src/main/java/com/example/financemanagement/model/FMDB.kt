package com.example.financemanagement.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.financemanagement.common.TestDataMaker
import com.example.financemanagement.common.d
import com.example.financemanagement.common.mContext

class FMDB : SQLiteOpenHelper {


    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(
        context,
        name,
        factory,
        version
    )

    private val TB_Finance = "Create Table Finance(FinanceId integer primary key autoincrement," +
            "Type text," +
            "Item text," +
            "Category text," +
            "Amount text," +
            "CreateTime)"

    private val TB_Category = "Create table $TBNM_Category(CategoryId integer primary key autoincrement," +
            "CategoryName text,Type text)"


    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(TB_Finance)
            db.execSQL(TB_Category)

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        "onUpgrade".d("D")
        if (db != null) {
            db.execSQL("drop table  if exists $TBNM_Finance")
            db.execSQL("drop table if exists $TBNM_Category ")
            onCreate(db)
        }
    }

    companion object {
        const val DBName = "FMDB.db"
        const val VERSION = 3
        const val TBNM_Finance = "Finance"
        const val TBNM_Category = "Category"
        private var db_instance: FMDB? = null
        //get instance
        fun getInstance(): FMDB {
            if (db_instance == null) {
                db_instance =
                    FMDB(
                        mContext,
                        DBName,
                        null,
                        VERSION
                    )
            }
            return db_instance!!
        }


    }


}