package com.shahin.meistersearch.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

/**
 * Base Activity class representing default properties of an activity
 *
 * Because we're using DataBindingUtil the variable
 * @param layoutResId is also used in the constructor
 */
abstract class BaseActivity<T: ViewBinding>(@LayoutRes private val layoutResId : Int): AppCompatActivity() {

    private var _binding : T? = null
    protected val binding : T get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}