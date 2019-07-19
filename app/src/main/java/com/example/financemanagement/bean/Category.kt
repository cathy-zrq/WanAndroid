package com.example.financemanagement.bean

import java.io.Serializable

class Category : Serializable {
    var CategoryId = 0
    var Type = ""
    var IsSelected = false
    var CategoryName = ""
    override fun toString(): String {
        return CategoryName
    }
}