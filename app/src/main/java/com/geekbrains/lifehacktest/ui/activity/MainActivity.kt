package com.geekbrains.lifehacktest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.lifehacktest.R
import com.geekbrains.lifehacktest.mvp.presenter.MainPresenter
import com.geekbrains.lifehacktest.mvp.utils.Constants
import com.geekbrains.lifehacktest.mvp.view.MainView
import com.geekbrains.lifehacktest.ui.adapter.ItemsListRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter(AndroidSchedulers.mainThread())

    private var rvAdapter: ItemsListRVAdapter? = null

    //region activity methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //endregion

    //region view methods
    override fun initList() {
        val layoutManager = LinearLayoutManager(baseContext)
        rvAdapter = ItemsListRVAdapter(presenter.itemsListPresenter)

        itemsRecyclerView.layoutManager = layoutManager
        itemsRecyclerView.adapter = rvAdapter
    }

    override fun updateList() {
        rvAdapter?.notifyDataSetChanged()
    }

    override fun showNoItems() {
        noItemsTextView.visibility = View.VISIBLE
    }

    override fun initSwipeToRefresh() {
        mainSwipeRefreshLayout.setOnRefreshListener {
            presenter.onSwipeRefreshDragged()
        }
    }

    override fun hideNoItems() {
        noItemsTextView.visibility = View.GONE
    }

    override fun hideProgressBars() {
        progressBarMain.visibility = View.GONE
        mainSwipeRefreshLayout.isRefreshing = false
    }

    override fun showDetailsScreen(id: String) {
        val intent = Intent(this, DetailedScreenActivity::class.java).apply {
            putExtra(Constants.itemIdFromMainScreenKey, id)
        }
        startActivity(intent)
    }
    //endregion
}
