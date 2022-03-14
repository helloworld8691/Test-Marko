package com.sts.test_marko.ui.pickingList

import androidx.lifecycle.Observer
import com.sts.test_marko.R
import com.sts.test_marko.common.BaseFragment
import com.sts.test_marko.custom_view.LoadingDialog
import com.sts.test_marko.databinding.FrmPickingListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PickingListFragment : BaseFragment<FrmPickingListBinding>(R.layout.frm_picking_list) {

    private val viewModel by viewModel<PickingListViewModel>()
    private val pickingListAdapter by lazy { PickingListAdapter() }

    private val loadingDialog by lazy {
        LoadingDialog(requireActivity()).apply {
            lifecycle.addObserver(this)
        }
    }

    override fun onStart() {
        super.onStart()

        binding.pickingListAdapter = pickingListAdapter
        binding.viewModel = viewModel

        viewModel.getData()
        viewModel.isApiRunning.observe(viewLifecycleOwner, Observer { loadingDialog.toggle(it) })

        viewModel.pickingList.observe(viewLifecycleOwner, Observer {
            pickingListAdapter.loadData(it)
        })
    }
}