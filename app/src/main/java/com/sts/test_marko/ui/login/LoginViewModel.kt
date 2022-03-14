package com.sts.test_marko.ui.login

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sts.test_marko.api.ApiInterface
import com.sts.test_marko.api.ApiManager
import com.sts.test_marko.api.ApiEndpoint
import com.sts.test_marko.model.UserModel
import com.sts.test_marko.model.WarehouseModel
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class LoginViewModel() : ViewModel(), ApiInterface {

    val userModel = UserModel()
    var warehouseModel = WarehouseModel()

    val usernameErrorLiveData = MutableLiveData<String>()
    val pwdErrorLiveData = MutableLiveData<String>()
    val isApiRunning = MutableLiveData<Boolean>(false)
    val bearerToken = MutableLiveData<String>()

    fun login(){
        when{
            TextUtils.isEmpty(userModel.username) -> usernameErrorLiveData.postValue("Please input username")
            TextUtils.isEmpty(userModel.password) -> pwdErrorLiveData.postValue("Please input password")
            else -> {
                val body = mapOf(
                    "grant_type" to "password",
                    "username" to "android_dev_user1",
                    "password" to "android_dev_user1",
                    "scope" to "packing"
                )
                ApiManager().post(ApiEndpoint.login, body, this)
            }
        }
    }

    fun getUserDetail(){

    }

    fun getWarehouseDetail(){

    }

    override fun onRunning() {
        isApiRunning.postValue(true)
    }

    override fun onSuccess(response: Response, endpoint: String) {
        isApiRunning.postValue(false)

        val jsonRes = JSONObject(response.body?.string()).toString()
        val data = Gson().fromJson(jsonRes, UserModel::class.java)

        bearerToken.postValue(data.access_token)
    }

    override fun onError(e: IOException, endpoint: String) {
        isApiRunning.postValue(false)
    }

}