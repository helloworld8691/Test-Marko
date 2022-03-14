package com.sts.test_marko.api

import android.text.TextUtils
import com.google.gson.Gson
import com.sts.test_marko.common.Constants
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiManager() {

    lateinit var client : OkHttpClient
    companion object {
        var bearerToken = ""
    }

    init {
        client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    public fun post(endpoint : String, params : Map<String, Any>, apiInterface: ApiInterface) {
        val body = RequestBody.create(Constants.API_MEDIA_TYPE.toMediaType(), Gson().toJson(params))
        val request = Request.Builder()
            .addHeader("Authorization", "Bearer ".plus(bearerToken))
            .method("POST", body)
            .url(Constants.BASE_URL.plus(endpoint))
            .build()

        apiInterface.onRunning()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                apiInterface.onError(e, endpoint)
            }

            override fun onResponse(call: Call, response: Response) {
                apiInterface.onSuccess(response, endpoint)
            }
        })
    }

    public fun get(endpoint: String, params: Map<String, String>, apiInterface: ApiInterface) {

        // ** add params into url //
        var reqParams = ""
        params.forEach{(key, value) ->
            reqParams = reqParams.plus(key).plus("=").plus(value).plus("&")
        }
        if (!TextUtils.isEmpty(reqParams)){
           reqParams = reqParams.dropLast(1)
        }
        // ** END ** //

        val request = Request.Builder()
            .addHeader("Authorization", "Bearer ".plus(bearerToken))
            .url(Constants.BASE_URL.plus(endpoint).plus(reqParams)).build()

        apiInterface.onRunning()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                apiInterface.onError(e, endpoint)
            }

            override fun onResponse(call: Call, response: Response) {
                apiInterface.onSuccess(response, endpoint)
            }
        })
    }
}