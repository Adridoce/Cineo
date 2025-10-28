package com.adridoce.cineo.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _selectedTab = MutableStateFlow(1)
    val selectedTab = _selectedTab.asStateFlow()

    fun onSelectedTab(index:Int){
        _selectedTab.value = index
    }
}