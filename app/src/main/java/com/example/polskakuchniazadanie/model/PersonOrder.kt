package com.example.polskakuchniazadanie.model

data class PersonOrder(
    val items: MutableList<MealItem> = mutableListOf()
) {
    fun addItem(item: MealItem) {
        items.add(item)
    }

    fun getTotal(): Double {
        return items.sumOf { it.price }
    }
}