package com.shahin.meistersearch.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.shahin.meistersearch.data.Repository

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    fun getToken(): String {
        return repository.getToken()
    }

}