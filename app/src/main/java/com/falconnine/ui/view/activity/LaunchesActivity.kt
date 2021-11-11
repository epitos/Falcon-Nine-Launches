package com.falconnine.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.falconnine.R
import com.falconnine.network.APIClient
import com.falconnine.network.APIInterface
import com.falconnine.network.model.Launch
import com.falconnine.ui.LaunchesContract
import com.falconnine.ui.presenter.LaunchesPresenter
import com.falconnine.ui.view.adapter.LaunchesAdapter
import kotlinx.android.synthetic.main.activity_main.*


class LaunchesActivity: AppCompatActivity(), LaunchesContract.View  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = LaunchesPresenter(this,
            APIClient.getClient().create(APIInterface::class.java))
        presenter.getLaunches()
    }

    override fun showLaunches(launches: ArrayList<Launch>) {
        val adapter = LaunchesAdapter(this, launches)
        val manager = LinearLayoutManager(this)
        launches_list.addItemDecoration(DividerItemDecoration(
            this, DividerItemDecoration.VERTICAL))
        launches_list.layoutManager = manager
        launches_list.adapter = adapter
    }

    override fun showProgressBar(visibility: Int) {
        progressbar.visibility = visibility
    }
}