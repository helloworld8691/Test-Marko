package com.sts.test_marko.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sts.test_marko.Utils.toast
import com.sts.test_marko.api.ApiInterface
import com.sts.test_marko.api.ApiManager
import com.sts.test_marko.api.ApiEndpoint
import com.sts.test_marko.model.UserModel
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

@SuppressLint("StaticFieldLeak")
class LoginViewModel (private val context: Context) : ViewModel(), ApiInterface {

    val userModel = UserModel()

    val usernameErrorLiveData = MutableLiveData<String>()
    val pwdErrorLiveData = MutableLiveData<String>()
    val isApiRunning = MutableLiveData<Boolean>(false)
    val bearerToken = MutableLiveData<String>()

    fun login(){
        when{
            TextUtils.isEmpty(userModel.username) -> {
                usernameErrorLiveData.postValue("Please input username")
                "Please input username".toast(context)
            }
            TextUtils.isEmpty(userModel.password) -> {
                pwdErrorLiveData.postValue("Please input password")
                "Please input password".toast(context)
            }
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

    override fun onRunning() {
        isApiRunning.postValue(true)
    }

    override fun onSuccess(response: Response, endpoint: String) {
        isApiRunning.postValue(false)

        val jsonRes = JSONObject(response.body!!.string()).toString()
        val data = Gson().fromJson(jsonRes, UserModel::class.java)

        bearerToken.postValue(data.access_token)
    }

    override fun onError(e: IOException, endpoint: String) {
        isApiRunning.postValue(false)
        e.localizedMessage.toast(context)
    }

}