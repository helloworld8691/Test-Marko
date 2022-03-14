package com.sts.test_marko.ui.login

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sts.test_marko.common.BaseFragment
import com.sts.test_marko.R
import com.sts.test_marko.api.ApiEndpoint
import com.sts.test_marko.api.ApiManager
import com.sts.test_marko.custom_view.LoadingDialog
import com.sts.test_marko.databinding.FrmLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FrmLoginBinding>(R.layout.frm_login) {

    private val viewModel by viewModel<LoginViewModel>()
    private val loadingDialog by lazy {
        LoadingDialog(requireActivity()).apply {
            lifecycle.addObserver(this)
        }
    }

    override fun FrmLoginBinding.initialized() {}

    override fun onStart() {
        super.onStart()

        binding.viewModel = viewModel

        viewModel.isApiRunning.observe(viewLifecycleOwner, Observer { loadingDialog.toggle(it) })
        viewModel.bearerToken.observe(viewLifecycleOwner, Observer {
            if (!TextUtils.isEmpty(it)){
                ApiManager.bearerToken = it
                findNavController().navigate(R.id.action_loginFragment_to_pickingListFragment2)
            }
        })
    }
}