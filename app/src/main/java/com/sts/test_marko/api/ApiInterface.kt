package com.sts.test_marko.api

import okhttp3.Response
import java.io.IOException

interface ApiInterface {
    fun onRunning()
    fun onSuccess(response: Response, endpoint : String)
    fun onError(e : IOException, endpoint: String)
}