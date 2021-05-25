package com.shahin.meistersearch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base Fragment class representing default properties of a fragment
 *
 * Because we're using DataBindingUtil the variable
 * @param layoutResId is also used in the constructor
 */
abstract class BaseFragment<T: ViewBinding>(@LayoutRes private val layoutResId : Int): Fragment() {

    private var _binding : T? = null
    protected val binding : T get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}