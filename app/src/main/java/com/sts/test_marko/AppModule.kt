package com.sts.test_marko

import android.app.Application
import android.content.Context
import com.sts.test_marko.api.ApiManager
import com.sts.test_marko.ui.login.LoginViewModel
import com.sts.test_marko.ui.pickingList.PickingListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module{
    viewModel { LoginViewModel(get()) }
    viewModel { PickingListViewModel() }
}