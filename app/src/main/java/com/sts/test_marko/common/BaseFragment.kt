package com.sts.test_marko.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sts.test_marko.custom_view.LoadingDialog

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutId : Int) : Fragment()  {

    private var _binding : T? = null
    val binding : T get() = _binding!!

    open  fun T.initialized(){}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.initialized()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}