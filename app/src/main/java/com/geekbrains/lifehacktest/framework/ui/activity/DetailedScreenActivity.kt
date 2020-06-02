package com.geekbrains.lifehacktest.framework.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.geekbrains.App
import com.geekbrains.lifehacktest.R
import com.geekbrains.lifehacktest.mvp.model.IIdProvider
import com.geekbrains.lifehacktest.mvp.presenter.DetailedPresenter
import com.geekbrains.lifehacktest.mvp.utils.Constants
import com.geekbrains.lifehacktest.mvp.view.DetailedView
import com.geekbrains.lifehacktest.mvp.model.image.IImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_detailed.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class DetailedScreenActivity: MvpAppCompatActivity(), DetailedView, IIdProvider {
    @InjectPresenter
    lateinit var presenter: DetailedPresenter

    @ProvidePresenter
    fun providePresenter() = DetailedPresenter(this, AndroidSchedulers.mainThread())

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    //region activity methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        App.appInstance.appComponent.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            presenter.onBackClicked()
        }

        return true
    }
    //endregion

    override fun getId(): String? = intent.getStringExtra(Constants.itemIdFromMainScreenKey)

    //region view methods
    override fun showContentView() {
        detailedContentView.visibility = View.VISIBLE
    }

    override fun setImage(url: String) {
        imageLoader.loadInto(url, detailedImageView)
    }

    override fun setName(text: String) {
        detailedNameTextView.text = text
    }

    override fun setDescription(text: String) {
        descriptionTextView.text = text
    }

    override fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setupFindUsBtn() {
        findUsBtn.setOnClickListener {
            presenter.onFindUsBtnClicked()
        }
    }

    override fun hideFindUsBtn() {
        findUsBtn.visibility = View.INVISIBLE
    }

    override fun showPositionOnMap(latitude: Float, longitude: Float) {
        val geoUri = Uri.parse("geo:$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, geoUri)
        startActivity(intent)
    }

    override fun setupWWWView() {
        wwwLayout.setOnClickListener {
            presenter.onWWWClicked()
        }
    }

    override fun setWWW(urlText: String) {
        wwwTextView.text = urlText
    }

    override fun hideWWW() {
        wwwLayout.visibility = View.GONE
    }

    override fun showUrlInBrowser(urlText: String) {
        val url = if(!urlText.startsWith("http")) "http://$urlText" else urlText
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun setupPhoneView() {
        phoneLayout.setOnClickListener {
            presenter.onPhoneClicked()
        }
    }

    override fun setPhoneNmb(text: String) {
        phoneTextView.text = text
    }

    override fun openPhoneDialer(phoneNmb: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNmb"))
        startActivity(intent)
    }

    override fun hidePhoneNmb() {
        phoneLayout.visibility = View.GONE
    }

    override fun showErrorLoading() {
        errorLoadingTextView.visibility = View.VISIBLE
    }

    override fun hideErrorLoading() {
        errorLoadingTextView.visibility = View.GONE
    }

    override fun hideLoaders() {
        detailedProgressBar.visibility = View.GONE
    }

    override fun goBack() {
        finish()
    }
    //endregion
}