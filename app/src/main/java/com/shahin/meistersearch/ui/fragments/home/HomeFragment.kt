package com.shahin.meistersearch.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.shahin.meistersearch.R
import com.shahin.meistersearch.data.models.Model
import com.shahin.meistersearch.databinding.FragmentHomeBinding
import com.shahin.meistersearch.extensions.visible
import com.shahin.meistersearch.ui.fragments.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.model = Model("Shahin", "Montazeri")

        binding.tv.visible()
    }

}