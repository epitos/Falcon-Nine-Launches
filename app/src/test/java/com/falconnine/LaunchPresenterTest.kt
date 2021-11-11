package com.falconnine

import android.view.View
import com.falconnine.network.APIInterface
import com.falconnine.ui.LaunchesContract
import com.falconnine.ui.presenter.LaunchesPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class LaunchPresenterTest {
    private lateinit var apiInterface: APIInterface
    private lateinit var view: LaunchesContract.View
    private lateinit var presenter: LaunchesPresenter

    @Before
    fun setup() {
        apiInterface = mock()
        view = mock()
        presenter = LaunchesPresenter(view, apiInterface)
    }

    @Test
    fun showLoadingTest() {
        presenter.getLaunches()
        verify(view).showProgressBar(View.VISIBLE)
    }
}