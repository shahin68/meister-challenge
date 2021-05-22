package com.shahin.meistersearch.di

import com.shahin.meistersearch.ui.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { HomeViewModel() }
}