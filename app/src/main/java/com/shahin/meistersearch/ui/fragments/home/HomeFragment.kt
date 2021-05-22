package com.shahin.meistersearch.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.shahin.meistersearch.R
import com.shahin.meistersearch.data.local.models.Model
import com.shahin.meistersearch.databinding.FragmentHomeBinding
import com.shahin.meistersearch.extensions.visible
import com.shahin.meistersearch.ui.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.model = Model("Shahin", "Montazeri")

        binding.tv.visible()
    }

}