package com.example.polskakuchniazadanie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.polskakuchniazadanie.model.MealItem
import com.example.polskakuchniazadanie.model.Order
import com.example.polskakuchniazadanie.model.PersonOrder

class OrderViewModel: ViewModel() {


    private var currentPersonOrder = PersonOrder()

    private val allOrders = Order()
    
    
    //-----------------------------------|TOTALBAR LIVE DATA|--------------------------------------------------------------------------------------------
    private val _currentOrderTotal = MutableLiveData(0.0) // obecna cena zamówienia do total bar
    val currentOrderTotal: LiveData<Double> get() = _currentOrderTotal

    private val _allOrdersTotal = MutableLiveData(0.0) // całkowita cena zamówienia do total bar
    val allOrdersTotal: LiveData<Double> get() = _allOrdersTotal


    private fun updateCurrentTotal() {
        _currentOrderTotal.value = currentPersonOrder.getTotal()
    }

    private fun updateAllOrdersTotal() {
        _allOrdersTotal.value = allOrders.getTotal()
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------


    fun addMealToCurrentOrder(item: MealItem) {
        currentPersonOrder.addItem(item)
        updateCurrentTotal()
        
    }

    fun confirmCurrentOrder() {
        allOrders.addOrder(currentPersonOrder)
        updateAllOrdersTotal()

        // zaczynamy następne zamówienie od zera
        currentPersonOrder = PersonOrder()
        updateCurrentTotal()

        
    }

    fun clearAllOrders() {
        allOrders.personOrders.clear()
        currentPersonOrder = PersonOrder()

        updateCurrentTotal()
        updateAllOrdersTotal()

    }



}