package com.shahin.meistersearch.di

import com.shahin.meistersearch.ui.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * module responsible for creation of UI related classes such
 */
val uiModule = module {
    viewModel { HomeViewModel(get()) }
}