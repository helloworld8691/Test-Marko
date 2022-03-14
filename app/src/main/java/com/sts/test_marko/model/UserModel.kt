package com.sts.test_marko.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

data class UserModel(
    var username: String = "",
    var password: String = "",
    var access_token: String = "",
    var expires_is: Int = 0,
    var scope: String = "",
    var token_type: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var email: String = "",
)
