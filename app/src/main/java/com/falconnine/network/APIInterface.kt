package com.falconnine.network

import com.falconnine.network.model.Response
import com.falconnine.utils.Constants.GET_LAUNCHES
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET(GET_LAUNCHES)
    open fun getLaunches(): Call<Response>
}