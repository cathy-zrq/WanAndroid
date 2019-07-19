package com.example.financemanagement.model

import android.content.ContentValues
import com.example.financemanagement.bean.Finance

class FinanceService {
    companion object {

        fun getAll(): ArrayList<Finance> {
            var db = FMDB.getInstance().readableDatabase
            var cursor = db.query(FMDB.TBNM_Finance, null, null, null, null, null, null)
            var list = arrayListOf<Finance>()
            while (cursor.moveToNext()) {
                list.add(Finance().apply {
                    FinanceId = cursor.getInt(cursor.getColumnIndex("FinanceId"))
                    Type = cursor.getString(cursor.getColumnIndex("Type"))
                    Category = cursor.getString(cursor.getColumnIndex("Category"))
                    Amount = cursor.getDouble(cursor.getColumnIndex("Amount"))
                    CreateTime = cursor.getString(cursor.getColumnIndex("CreateTime"))
                    Item = cursor.getString(cursor.getColumnIndex("Item"))
                })
            }
            db.close()
            return list
        }

        fun edit(item: Finance): Unit {
            var db = FMDB.getInstance().writableDatabase
            var contentValues = ContentValues()
                .apply {
                    put("Type", item.Type)
                    put("Category", item.Category)
                    put("Amount", item.Amount)
                    put("CreateTime", item.CreateTime)
                    put("Item", item.Item)
                }
            db.update(FMDB.TBNM_Finance, contentValues, "FinanceId=${item.FinanceId}", null)
            db.close()
        }


        //add a item
        fun add(item: Finance): Unit {
            var db = FMDB.getInstance().writableDatabase
            var contentValues = ContentValues()
                .apply {
                    put("Type", item.Type)
                    put("Category", item.Category)
                    put("Amount", item.Amount)
                    put("CreateTime", item.CreateTime)
                    put("Item", item.Item)
                }
            db.insert(FMDB.TBNM_Finance, null, contentValues)
            db.close()
        }


        fun del(id: Int): Unit {
            var db = FMDB.getInstance().writableDatabase
            db.delete(FMDB.TBNM_Finance, "FinanceId=${id}", null)
            db.close()
        }


    }
}