package com.geekbrains.lifehacktest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.lifehacktest.R
import com.geekbrains.lifehacktest.mvp.presenter.list.IItemsListPresenter
import com.geekbrains.lifehacktest.mvp.view.list.ShortItemView
import com.geekbrains.lifehacktest.ui.image.GlideImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.view.*

class ItemsListRVAdapter(private val presenter: IItemsListPresenter):
    RecyclerView.Adapter<ItemsListRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer, ShortItemView {
        private val imageLoader = GlideImageLoader()

        override var pos = -1

        override fun setTitle(text: String) = with(containerView) {
            itemTextView.text = text
        }

        override fun setImage(url: String) = with(containerView) {
            imageLoader.loadInto(url, itemImageView)
        }
    }
}