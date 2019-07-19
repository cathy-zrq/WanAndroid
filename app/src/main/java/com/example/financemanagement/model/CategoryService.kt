package com.example.financemanagement.model

import android.content.ContentValues
import com.example.financemanagement.bean.Category

class CategoryService {
    companion object {
        fun add(category: Category): Unit {
            var db = FMDB.getInstance().writableDatabase
            var contentValues = ContentValues()
                .apply {
                    put("CategoryName", category.CategoryName)
                    put("Type", category.Type)
                }
            db.insert(FMDB.TBNM_Category, null, contentValues)
        }

        fun edit(category: Category): Unit {
            var db = FMDB.getInstance().writableDatabase
            var contentValues = ContentValues()
                .apply {
                    put("CategoryName", category.CategoryName)
                    put("Type", category.Type)
                }
            db.update(FMDB.TBNM_Category, contentValues, "CategoryId=${category.CategoryId}", null)
        }

        fun getAll(): ArrayList<Category> {
            var db = FMDB.getInstance().readableDatabase
            var cursor = db.query(FMDB.TBNM_Category, null, null, null, null, null, null)
            var list = arrayListOf<Category>()
            while (cursor.moveToNext()) {
                list.add(Category().apply {
                    CategoryId = cursor.getInt(cursor.getColumnIndex("CategoryId"))
                    CategoryName = cursor.getString(cursor.getColumnIndex("CategoryName"))
                    Type = cursor.getString(cursor.getColumnIndex("Type"))
                })
            }
            db.close()
            return list
        }


        fun del(id: Int): Unit {
            var db = FMDB.getInstance().writableDatabase
            db.delete(FMDB.TBNM_Category, "CategoryId=${id}", null)
            db.close()
        }


    }
}