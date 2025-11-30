package com.example.polskakuchniazadanie.model

data class Order(
    val personOrders: MutableList<PersonOrder> = mutableListOf()
) {
    fun addOrder(order: PersonOrder) {
        personOrders.add(order)
    }

    fun getTotal(): Double {
        return personOrders.sumOf { it.getTotal() }
    }
}