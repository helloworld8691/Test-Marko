package com.sts.test_marko.ui.pickingList

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sts.test_marko.api.ApiInterface
import com.sts.test_marko.api.ApiManager
import com.sts.test_marko.api.ApiEndpoint
import com.sts.test_marko.model.UserModel
import com.sts.test_marko.model.WarehouseModel
import okhttp3.HttpUrl
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class PickingListViewModel : ViewModel(), ApiInterface {

    val userModel = MutableLiveData<UserModel>()
    val warehouseModel = MutableLiveData<WarehouseModel>()

    val isApiRunning = MutableLiveData<Boolean>(false)

    fun getData(){
        ApiManager().get(ApiEndpoint.fetch_user, this)
        ApiManager().get(ApiEndpoint.fetch_warehouse, this)


        ApiManager().get(ApiEndpoint.fetch_picking_orders
            .plus("?state=").plus("queue")
            .plus("&warehouse_id").plus("817"), this)
    }

    override fun onRunning() {
        isApiRunning.postValue(true)
    }

    override fun onSuccess(response: Response, endpoint: String) {
        isApiRunning.postValue(false)

        if (TextUtils.equals(endpoint, ApiEndpoint.fetch_user)){
            val jsonRes = JSONObject(response.body?.string()).toString()
            userModel.postValue(Gson().fromJson(jsonRes, UserModel::class.java))
        }

        if (TextUtils.equals(endpoint, ApiEndpoint.fetch_warehouse)) {
            val jsonRes = JSONObject(response.body?.string()).getJSONArray("warehouses")[0].toString()
            warehouseModel.postValue(Gson().fromJson(jsonRes, WarehouseModel::class.java))
        }
    }

    override fun onError(e: IOException, endpoint: String) {
        isApiRunning.postValue(false)
    }
}