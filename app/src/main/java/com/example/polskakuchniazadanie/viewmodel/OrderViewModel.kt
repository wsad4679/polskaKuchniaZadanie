package com.example.polskakuchniazadanie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.polskakuchniazadanie.model.MealItem
import com.example.polskakuchniazadanie.model.Order
import com.example.polskakuchniazadanie.model.PersonOrder

class OrderViewModel: ViewModel() {


    private val _currentPersonOrder = MutableLiveData<PersonOrder>(PersonOrder())
    val currentPersonOrder: LiveData<PersonOrder> get() = _currentPersonOrder

    private val _allOrders = MutableLiveData<Order>(Order())
    val allOrders: LiveData<Order> get() = _allOrders
    
    
    //-----------------------------------|TOTALBAR LIVE DATA|--------------------------------------------------------------------------------------------
    private val _currentOrderTotal = MutableLiveData(0.0) // obecna cena zamówienia do total bar
    val currentOrderTotal: LiveData<Double> get() = _currentOrderTotal

    private val _allOrdersTotal = MutableLiveData(0.0) // całkowita cena zamówienia do total bar
    val allOrdersTotal: LiveData<Double> get() = _allOrdersTotal


    private fun updateCurrentTotal() {
        _currentOrderTotal.value = currentPersonOrder.value?.getTotal()
    }

    private fun updateAllOrdersTotal() {
         val total = (allOrders.value?.getTotal() ?: 0.0) + (currentPersonOrder.value?.getTotal() ?: 0.0)
         _allOrdersTotal.value = total
        Log.i("test", total.toString())

    }
//----------------------------------------------------------------------------------------------------------------------------------------------------


    fun addMealToCurrentOrder(item: MealItem) {
        val order = _currentPersonOrder.value ?: PersonOrder()
        order.addItem(item)
        _currentPersonOrder.value = order // aktualizacja LiveData
        updateCurrentTotal()
        updateAllOrdersTotal()
    }

    fun confirmCurrentOrder() {
        val orderList = _allOrders.value ?: Order()
        val currentOrder = _currentPersonOrder.value ?: PersonOrder()

        orderList.addOrder(currentOrder)
        _allOrders.value = orderList // aktualizacja LiveData

        // reset bieżącego zamówienia
        _currentPersonOrder.value = PersonOrder()
        updateCurrentTotal()
    }

    fun clearAllOrders() {
        _allOrders.value = Order()
        _currentPersonOrder.value = PersonOrder()
        updateCurrentTotal()
        updateAllOrdersTotal()
    }

}



