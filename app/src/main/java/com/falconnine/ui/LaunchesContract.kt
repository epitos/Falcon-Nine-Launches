package com.falconnine.ui

import com.falconnine.network.model.Launch

interface LaunchesContract {

    interface Presenter {
        fun getLaunches()
    }

    interface View {
        fun showLaunches(launches: ArrayList<Launch>)
        fun showProgressBar(visibility: Int)
    }
}