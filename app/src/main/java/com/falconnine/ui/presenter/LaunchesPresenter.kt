package com.falconnine.ui.presenter

import android.util.Log
import android.view.View
import com.falconnine.network.APIClient
import com.falconnine.network.APIInterface
import com.falconnine.network.model.Launch
import com.falconnine.network.model.ResponseItem
import com.falconnine.ui.LaunchesContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

open class LaunchesPresenter(private val view: LaunchesContract.View,
                             private val apiInterface: APIInterface): LaunchesContract.Presenter {

    override fun getLaunches() {
        view.showProgressBar(View.VISIBLE)
        CoroutineScope(Dispatchers.IO).launch {
            val call = apiInterface.getLaunches()

            call.enqueue(object : retrofit2.Callback<com.falconnine.network.model.Response?>{
                override fun onFailure(call: Call<com.falconnine.network.model.Response?>,
                                       t: Throwable) {
                    Log.d("LaunchesPresenter", "failure")
                    view.showProgressBar(View.GONE)
                }

                override fun onResponse(call: Call<com.falconnine.network.model.Response?>,
                                        response: Response<com.falconnine.network.model.Response?>) {
                    if (response.isSuccessful) {
                        view.showProgressBar(View.GONE)
                        setLaunches(response)
                    }
                }
            })
        }
    }

    private fun setLaunches(response: Response<com.falconnine.network.model.Response?>) {
        val responseBody = response.body()
        var launchList = ArrayList<Launch>()

        for (launch in responseBody!!.iterator()) {
            launchList.add(Launch(launch.name, launch.date_local,
                launch.success, launch.links.patch.small))
        }
        view.showLaunches(launchList)
    }
}