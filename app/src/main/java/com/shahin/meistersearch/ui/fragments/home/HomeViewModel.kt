package com.shahin.meistersearch.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.local.models.Model

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    fun getModel(): Model {
        return repository.getModel()
    }

}