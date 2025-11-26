package com.example.polskakuchniazadanie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel: ViewModel() {
    private val _orderDescription = MutableLiveData<String>()
    val orderDescription : LiveData<String> get() = _orderDescription

    fun sendOrderDescription (description: String){
        _orderDescription.value = description
    }

}